package controller;

import static domain.rule.BettingRule.INITIAL_MAX_VALUE;
import static domain.rule.BettingRule.INITIAL_MIN_VALUE;
import static domain.rule.ParticipantsRule.PARTICIPANTS_MAX_COUNT;
import static domain.rule.ParticipantsRule.PARTICIPANTS_MIN_COUNT;
import static view.resource.OutputContent.FORMAT_ASK_BETTING_MONEY;
import static view.resource.OutputContent.FORMAT_ASK_HIT_OR_STAY;
import static view.resource.OutputContent.FORMAT_FUNCTION_ERROR;
import static view.resource.OutputContent.FORMAT_INFORM_DISTRIBUTED;
import static view.resource.OutputContent.MESSAGE_ASK_PLAYERS_NAME;
import static view.resource.OutputContent.MESSAGE_INFORM_DEALER_BLACKJACK;
import static view.resource.OutputContent.MESSAGE_INFORM_TOTAL_EARNINGS;

import domain.card.CardDistributor;
import model.BlackJackGame;
import model.dto.Earning;
import model.dto.Earnings;
import model.dto.OpenedCards;
import model.dto.Opening;
import view.InputView;
import view.OutputView;
import view.resource.OutputContent;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final AnswerHandler answerHandler = new AnswerHandler(outputView);
    private BlackJackGame blackJackGame;
    private List<String> playerNames;

    public void run(CardDistributor distributor) {
        try {
            initializeGame(distributor);
            start();
            checkBlackJackAndPlay();
            showResult();
        } catch (Exception exception) {
            outputView.printFormattedMessage(FORMAT_FUNCTION_ERROR, exception.getMessage());
        }
    }

    private void initializeGame(CardDistributor distributor) {
        outputView.printMessage(MESSAGE_ASK_PLAYERS_NAME);
        playerNames = answerHandler.askUntilGetLegalAnswer(() -> inputView.readPlayersName(
                PARTICIPANTS_MIN_COUNT.getValue(), PARTICIPANTS_MAX_COUNT.getValue()
        ));
        outputView.printBlankLine();
        Map<String, Double> players = new LinkedHashMap<>();
        playerNames.forEach(name -> players.put(name, askBettingMoney(name)));
        blackJackGame = new BlackJackGame(distributor, players);
    }

    private double askBettingMoney(String playerName) {
        outputView.printFormattedMessage(FORMAT_ASK_BETTING_MONEY, playerName);
        double bettingMoney = answerHandler.askUntilGetLegalAnswer(() -> inputView.readBettingMoney(
                INITIAL_MIN_VALUE.getValue(), INITIAL_MAX_VALUE.getValue()
        ));
        outputView.printBlankLine();
        return bettingMoney;
    }

    private void start() {
        blackJackGame.deal();
        outputView.printFormattedMessage(FORMAT_INFORM_DISTRIBUTED, playerNames);
        showOpenedCards(blackJackGame.open(false));
        outputView.printBlankLine();
    }

    private void checkBlackJackAndPlay() {
        blackJackGame.updateEarningsByBlackJack();
        if (blackJackGame.isDealerBlackJack()) {
            outputView.printMessage(MESSAGE_INFORM_DEALER_BLACKJACK);
            return;
        }
        play();
    }

    private void play() {
        blackJackGame.getNotBlackJackPlayersName()
                .forEach(this::hitUntilPlayerWant);
        outputView.printBlankLine();
        hitIfDealerValid();
        blackJackGame.updateEarningsWithOutBlackJack();
    }

    private void hitUntilPlayerWant(String playerName) {
        boolean toHitMore = true;
        int hitCount = 0;
        while (toHitMore) {
            toHitMore = askHitOrStay(playerName);
            hitCount += addCardIfToHit(toHitMore, playerName);
        }
        if (hitCount == 0) {
            showOpenedCardsForPlayer(playerName);
        }
    }

    private int addCardIfToHit(boolean toHitMore, String playerName) {
        if (toHitMore) {
            blackJackGame.hitPlayer(playerName);
            showOpenedCardsForPlayer(playerName);
            return 1;
        }
        return 0;
    }

    private boolean askHitOrStay(String playerName) {
        outputView.printFormattedMessage(FORMAT_ASK_HIT_OR_STAY, playerName);
        return answerHandler.askUntilGetLegalAnswer(inputView::readHitOrStay);
    }

    private void hitIfDealerValid() {
        while (blackJackGame.hitDealer()) {
            outputView.printMessage(OutputContent.MESSAGE_INFORM_DEALER_HIT);
        }
    }

    private void showResult() {
        showOpenedCardsWithResult(blackJackGame.open(true));
        outputView.printBlankLine();
        outputView.printMessage(MESSAGE_INFORM_TOTAL_EARNINGS);
        showEarnings(blackJackGame.getEarnings());
    }

    private void showOpenedCards(Opening opening) {
        OpenedCards dealerOpening = opening.getDealerCards();
        outputView.printOpenedCards(dealerOpening.getName(), dealerOpening.getCards());
        opening.getPlayerCards()
                .forEach(cards -> outputView.printOpenedCards(cards.getName(), cards.getCards()));
    }

    private void showOpenedCardsWithResult(Opening opening) {
        OpenedCards dealerOpening = opening.getDealerCards();
        outputView.printOpenedCards(dealerOpening.getName(), dealerOpening.getCards(), dealerOpening.getScore());
        for (OpenedCards playerCards : opening.getPlayerCards()) {
            outputView.printOpenedCards(playerCards.getName(), playerCards.getCards(), playerCards.getScore());
        }
    }

    private void showOpenedCardsForPlayer(String playerName) {
        OpenedCards playerCards = blackJackGame.openPlayerCards(playerName);
        outputView.printOpenedCards(playerCards.getName(), playerCards.getCards());
    }

    private void showEarnings(Earnings earnings) {
        Earning dealerEarning = earnings.getDealerEarning();
        outputView.printEarning(dealerEarning.getName(), dealerEarning.getValue());
        earnings.getPlayerEarnings()
                .forEach(earning -> outputView.printEarning(earning.getName(), earning.getValue()));
    }
}
