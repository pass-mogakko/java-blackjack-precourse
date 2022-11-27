package controller;

import static domain.rule.BettingRule.INITIAL_MAX_VALUE;
import static domain.rule.BettingRule.INITIAL_MIN_VALUE;
import static domain.rule.ParticipantsRule.PARTICIPANTS_MAX_COUNT;
import static domain.rule.ParticipantsRule.PARTICIPANTS_MIN_COUNT;
import static view.resource.OutputContent.FORMAT_ASK_BETTING_MONEY;
import static view.resource.OutputContent.FORMAT_ASK_HIT_OR_STAY;
import static view.resource.OutputContent.FORMAT_INFORM_DISTRIBUTED;
import static view.resource.OutputContent.MESSAGE_ASK_PLAYERS_NAME;

import model.BlackJackGame;
import model.OpenedCardsDto;
import view.InputView;
import view.OutputView;

import java.util.List;

public class GameController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final BlackJackGame blackJackGame = new BlackJackGame();

    public void run() {
        // TODO 사용자 이름 출력 리팩토링
        List<String> playerNames = askPlayersName();
        playerNames.forEach(this::enrollWithBettingMoney);
        distributeCards(playerNames);
        hitOrStay(playerNames);
        showEarnings(playerNames);
    }

    private List<String> askPlayersName() {
        outputView.printMessage(MESSAGE_ASK_PLAYERS_NAME);
        List<String> playerNames = inputView.readPlayersName(PARTICIPANTS_MIN_COUNT.getValue(), PARTICIPANTS_MAX_COUNT.getValue());
        outputView.printBlankLine();
        return playerNames;
    }

    private void enrollWithBettingMoney(String playerName) {
        outputView.printFormattedMessage(FORMAT_ASK_BETTING_MONEY, playerName);
        double bettingMoney = inputView.readBettingMoney(INITIAL_MIN_VALUE.getValue(), INITIAL_MAX_VALUE.getValue());
        blackJackGame.enrollPlayer(playerName, bettingMoney);
        outputView.printBlankLine();
    }

    private void distributeCards(List<String> playerNames) {
        blackJackGame.distributeCardsForStart();
        OpenedCardsDto cards = blackJackGame.openAllUserCards(false);
        outputView.printFormattedMessage(FORMAT_INFORM_DISTRIBUTED, playerNames);
        outputView.printOpenedCards(cards);
        outputView.printBlankLine();
    }

    private void hitOrStay(List<String> playerNames) {
        for (String playerName : playerNames) {
            outputView.printFormattedMessage(FORMAT_ASK_HIT_OR_STAY, playerName);
            boolean toHit = inputView.readHitOrStay();
            // TODO 입력값에 따라 카드 지급, 오픈
        }
        // TODO dealer hit or stay
        // TODO dealer 안내메시지
    }

    private void showEarnings(List<String> playerNames) {
        // TODO 수익 계산하기
//        outputView.printEarnings(new EarningsDto(10000, Map.of("pobi", 1000.0)), playerNames);
    }
}
