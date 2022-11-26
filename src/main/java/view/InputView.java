package view;

import static view.resource.Format.NAMES_DELIMITER;

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

    private void validateDuplicatedName(List<String> trimmedNames) {
        if (new HashSet<>(trimmedNames).size() != trimmedNames.size()) {
            throw new IllegalArgumentException("중복된 참가자 이름이 존재합니다.");
        }
    }

    private void validateRange(int minimum, int maximum, int count) {
        if (count < minimum || count > maximum) {
            throw new IllegalArgumentException("잘못된 범위의 입력값입니다.");
        }
    }

    private static class ConsoleReader {
        private static final Scanner scanner = new Scanner(System.in);

        public static List<String> readFormattedLine(String delimiter) {
            String line = readLine();
            return Arrays.asList(line.split(delimiter));
        }

        private static String readLine() {
            return scanner.nextLine();
        }
    }
}
