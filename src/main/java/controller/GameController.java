package controller;

import static domain.rule.BettingRule.INITIAL_MAX_VALUE;
import static domain.rule.BettingRule.INITIAL_MIN_VALUE;
import static domain.rule.ParticipantsRule.PARTICIPANTS_MAX_COUNT;
import static domain.rule.ParticipantsRule.PARTICIPANTS_MIN_COUNT;
import static view.resource.OutputContent.FORMAT_ASK_BETTING_MONEY;
import static view.resource.OutputContent.FORMAT_ASK_HIT_OR_STAY;
import static view.resource.OutputContent.FORMAT_INFORM_DISTRIBUTED;
import static view.resource.OutputContent.MESSAGE_ASK_PLAYERS_NAME;
import static view.resource.OutputContent.MESSAGE_INFORM_DEALER_BLACKJACK;
import static view.resource.OutputContent.MESSAGE_INFORM_TOTAL_EARNINGS;

import domain.card.CardDistributor;
import model.BlackJackGame;
import model.OpenedCardsDto;
import view.InputView;
import view.OutputView;
import view.resource.OutputContent;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private BlackJackGame blackJackGame;
    private List<String> playerNames;

    public void run(CardDistributor distributor) {
        initializeGame(distributor);
        deal();
        checkBlackJackAndPlay();
        showResult();
    }

    private void initializeGame(CardDistributor distributor) {
        outputView.printMessage(MESSAGE_ASK_PLAYERS_NAME);
        playerNames = inputView.readPlayersName(PARTICIPANTS_MIN_COUNT.getValue(), PARTICIPANTS_MAX_COUNT.getValue());
        outputView.printBlankLine();
        Map<String, Double> players = new LinkedHashMap<>();
        playerNames.forEach(name -> players.put(name, askBettingMoney(name)));
        blackJackGame = new BlackJackGame(distributor, players);
    }

    private double askBettingMoney(String playerName) {
        outputView.printFormattedMessage(FORMAT_ASK_BETTING_MONEY, playerName);
        double bettingMoney = inputView.readBettingMoney(INITIAL_MIN_VALUE.getValue(), INITIAL_MAX_VALUE.getValue());
        outputView.printBlankLine();
        return bettingMoney;
    }

    private void deal() {
        blackJackGame.distributeCardsForStart();
        OpenedCardsDto cards = blackJackGame.openAllUserCards(false);
        outputView.printFormattedMessage(FORMAT_INFORM_DISTRIBUTED, playerNames);
        outputView.printOpenedCards(cards);
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
        playerNames.stream()
                .filter(name -> !blackJackGame.isPlayerBlackJack(name))
                .forEach(this::hitUntilPlayerWant);
        outputView.printBlankLine();
        hitIfDealerValid();
        blackJackGame.updateEarningsWithOutBlackJack();
    }

    private void hitIfDealerValid() {
        while (blackJackGame.addCardToDealerIfValid()) {
            outputView.printMessage(OutputContent.MESSAGE_INFORM_DEALER_HIT);
        }
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
            blackJackGame.addCardToPlayer(playerName);
            outputView.printOpenedCards(blackJackGame.openPlayerCards(playerName));
            return 1;
        }
        return 0;
    }

    private boolean askHitOrStay(String playerName) {
        outputView.printFormattedMessage(FORMAT_ASK_HIT_OR_STAY, playerName);
        return inputView.readHitOrStay();
    }

    private void showResult() {
        outputView.printOpenedCardsWithResult(blackJackGame.openAllUserCards(true));
        outputView.printBlankLine();
        outputView.printMessage(MESSAGE_INFORM_TOTAL_EARNINGS);
        outputView.printEarnings(blackJackGame.getEarnings(), playerNames);
    }
}
