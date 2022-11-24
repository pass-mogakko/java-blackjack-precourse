package domain.View;

import domain.InputValidator;
import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;






public class InputView {
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

    public double readBettingMoney() throws IllegalArgumentException {
        try {
            scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            InputValidator.validateBettingMoney(input);

            return Integer.parseInt(input);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw illegalArgumentException;
        }
    }
}
