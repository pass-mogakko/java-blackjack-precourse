package view;

import domain.exception.ExceptionMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static domain.user.Command.DRAW_CARD;
import static domain.user.Command.NOT_DRAW_CARD;

public class InputView {
    private static final String INPUT_PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_BETTING_MONEY = "의 베팅금액은?";
    private static final String INPUT_COMMAND = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String NAME_REGEX = "^[a-z]+$";
    private static final String MONEY_REGEX = "^[0-9]+$";
    private static final int START_MONEY_RANGE = 0;
    private static final int END_MONEY_RANGE = 100_000_000;

    private static final BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

    public static List<String> readPlayerName() throws IOException {
        System.out.println(INPUT_PLAYER_NAME);
        List<String> playerName = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(console.readLine(), ",");
        while (tokenizer.hasMoreTokens()) {
            String name = tokenizer.nextToken();
            validateName(name);
            playerName.add(name);
        }
        return playerName;
    }

    private static void validateName(String name) {
        if (!name.matches(NAME_REGEX)) {
            throw new IllegalArgumentException(ExceptionMessage.ONLY_ALPHABET);
        }
    }

    public static int readBettingMoney(String name) throws IOException {
        System.out.print(System.lineSeparator());
        System.out.println(name + INPUT_BETTING_MONEY);
        String bettingMoney = console.readLine();
        validateMoney(bettingMoney);
        validateMoneyRange(Integer.parseInt(bettingMoney));
        return Integer.parseInt(bettingMoney);
    }

    private static void validateMoney(String money) {
        if (!money.matches(MONEY_REGEX)) {
            throw new IllegalArgumentException(ExceptionMessage.ONLY_NUMBER);
        }
    }

    private static void validateMoneyRange(int money) {
        if (money <= START_MONEY_RANGE || money > END_MONEY_RANGE) {
            throw new IllegalArgumentException(ExceptionMessage.ONLY_CORRECT_MONEY);
        }
    }

    public static String readCommand(String name) throws IOException {
        System.out.print(System.lineSeparator());
        System.out.println(name + INPUT_COMMAND);
        String command = console.readLine();
        validateCommand(command);
        return command;
    }

    private static void validateCommand(String command) {
        if (!command.equals(DRAW_CARD.getCommand())
                && !command.equals(NOT_DRAW_CARD.getCommand())) {
            throw new IllegalArgumentException(ExceptionMessage.ONLY_YES_OR_NO);
        }
    }
}
