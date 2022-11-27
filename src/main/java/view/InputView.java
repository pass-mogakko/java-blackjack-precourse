package view;

import static view.resource.Format.NAMES_DELIMITER;

import view.resource.Command;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    public List<String> readPlayersName(int minimumCount, int maximumCount) {
        List<String> readValue = ConsoleReader.readFormattedLine(NAMES_DELIMITER.getValue());
        validateRange(minimumCount, maximumCount, readValue.size());
        List<String> names = readValue.stream()
                .map(String::trim)
                .collect(Collectors.toList());
        validateDuplicatedName(names);
        return names;
    }

    public double readBettingMoney(double minimumValue, double maximumValue) {
        double readValue = ConsoleReader.readLineAsDouble();
        validateRange(minimumValue, maximumValue, readValue);
        return readValue;
    }

    public boolean readHitOrStay() {
        String readValue = ConsoleReader.readLine();
        return Command.isToHitByCommand(readValue);
    }

    // TODO 검증 로직 클래스 분리
    private void validateDuplicatedName(List<String> trimmedNames) {
        if (new HashSet<>(trimmedNames).size() != trimmedNames.size()) {
            throw new IllegalArgumentException("중복된 참가자 이름이 존재합니다.");
        }
    }

    private void validateRange(int minimum, int maximum, int value) {
        if (value < minimum || value > maximum) {
            throw new IllegalArgumentException("잘못된 범위의 입력값입니다.");
        }
    }

    private void validateRange(double minimum, double maximum, double value) {
        if (value < minimum || value > maximum) {
            throw new IllegalArgumentException("잘못된 범위의 입력값입니다.");
        }
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