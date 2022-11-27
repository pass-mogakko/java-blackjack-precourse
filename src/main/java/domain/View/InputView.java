package domain.View;

import static domain.Constants.InputValue.SELECTION_NO;
import static domain.Constants.InputValue.SELECTION_YES;

import domain.Util.InputValidator;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;



public class InputView {
    private static final String BETTING_MONEY_REQUEST = "%s의 배팅 금액은?" + System.lineSeparator();
    private static final String HIT_REQUEST = "%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)" + System.lineSeparator();


    private Scanner scanner;


    public List<String> readPlayers() throws IllegalArgumentException {
        System.out.println("플레이어 입력");

        try {
            scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            InputValidator.validatePlayersInput(input);
            return Arrays.stream(input.split(","))
                    .distinct()
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException illegalArgumentException) {
            throw illegalArgumentException;
        }
    }

    public double readBettingMoney(String playerName) throws IllegalArgumentException {
        System.out.printf(BETTING_MONEY_REQUEST, playerName);

        try {
            scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            InputValidator.validateBettingMoney(input);

            return Integer.parseInt(input);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw illegalArgumentException;
        }
    }

    public String readSelection(String playerName) throws IllegalArgumentException {
        System.out.printf(HIT_REQUEST, playerName, SELECTION_YES, SELECTION_NO);

        try {
            scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            InputValidator.validateSelection(input);

            return input;
        } catch (IllegalArgumentException illegalArgumentException) {
            throw illegalArgumentException;
        }
    }
}
