package view;

import static view.resource.Format.NAMES_DELIMITER;

import view.resource.Command;
import view.validator.IOValidator;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    public List<String> readPlayersName(int minimumCount, int maximumCount) {
        List<String> readValue = ConsoleReader.readFormattedLine(NAMES_DELIMITER.getValue());
        IOValidator.validateRange(minimumCount, maximumCount, readValue.size());
        List<String> names = readValue.stream()
                .map(String::trim)
                .collect(Collectors.toList());
        IOValidator.validateDuplicatedName(names);
        return names;
    }

    public double readBettingMoney(double minimumValue, double maximumValue) {
        double readValue = ConsoleReader.readLineAsDouble();
        IOValidator.validateRange(minimumValue, maximumValue, readValue);
        return readValue;
    }

    public boolean readHitOrStay() {
        String readValue = ConsoleReader.readLine();
        return Command.isToHitByCommand(readValue);
    }

    private static class ConsoleReader {

        private static final Scanner scanner = new Scanner(System.in);

        public static List<String> readFormattedLine(String delimiter) {
            String line = readLine();
            return Arrays.asList(line.split(delimiter));
        }

        public static double readLineAsDouble() {
            try {
                return Double.parseDouble(readLine());
            } catch (NumberFormatException exception) {
                throw new IllegalArgumentException("숫자를 입력해주세요.");
            }
        }

        private static String readLine() {
            return scanner.nextLine();
        }
    }
}