package view.validator;

import java.util.HashSet;
import java.util.List;

public class IOValidator {

    public static void validateDuplicatedName(List<String> trimmedNames) {
        if (new HashSet<>(trimmedNames).size() != trimmedNames.size()) {
            throw new IllegalArgumentException("중복된 참가자 이름이 존재합니다.");
        }
    }

    public static void validateRange(int minimum, int maximum, int value) {
        if (value < minimum || value > maximum) {
            throw new IllegalArgumentException("잘못된 범위의 입력값입니다.");
        }
    }

    public static void validateRange(double minimum, double maximum, double value) {
        if (value < minimum || value > maximum) {
            throw new IllegalArgumentException("잘못된 범위의 입력값입니다.");
        }
    }

    public static void validateContent(Object content) {
        if (content == null) {
            throw new IllegalArgumentException("출력할 정보의 값이 null입니다.");
        }
    }
}
