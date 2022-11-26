package controller;

import static domain.rule.BettingRule.INITIAL_MAX_VALUE;
import static domain.rule.BettingRule.INITIAL_MIN_VALUE;
import static domain.rule.ParticipantsRule.PARTICIPANTS_MAX_COUNT;
import static domain.rule.ParticipantsRule.PARTICIPANTS_MIN_COUNT;
import static view.resource.OutputContent.FORMAT_ASK_BETTING_MONEY;
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
        List<String> playerNames = askPlayersName();
        playerNames.forEach(this::enrollWithBettingMoney);
        blackJackGame.distributeCardsForStart();
        // TODO 형식에 맞게 안내메시지 출력
        OpenedCardsDto cards = blackJackGame.openCards(false);
        // TODO 형식에 맞게 카드 오픈 출력
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
}
