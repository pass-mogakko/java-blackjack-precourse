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
            startGame();
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

    private void startGame() {
        blackJackGame.deal();
        Opening opening = blackJackGame.open(false);
        outputView.printFormattedMessage(FORMAT_INFORM_DISTRIBUTED, playerNames);
        outputView.printOpening(opening);
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
            outputView.printOpenedCards(blackJackGame.openPlayerCards(playerName));
        }
    }

    private int addCardIfToHit(boolean toHitMore, String playerName) {
        if (toHitMore) {
            blackJackGame.hitPlayer(playerName);
            outputView.printOpenedCards(blackJackGame.openPlayerCards(playerName));
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
        outputView.printResult(blackJackGame.open(true));
        outputView.printBlankLine();
        outputView.printMessage(MESSAGE_INFORM_TOTAL_EARNINGS);
        outputView.printEarnings(blackJackGame.getEarnings(), playerNames);
    }
}
