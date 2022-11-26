package util;

public enum ErrorCode {

    // 플레이어 이름 관련 오류
    BLANK_PLAYER_NAMES("[ERROR] 플레이어 이름은 공백이 될 수 없습니다."),
    INVALID_PLAYER_NAMES_TYPE("[ERROR] 플레이어 이름은 공백이 아닌 문자와 쉼표로만 입력하여 주십시오."),

    // 배팅 금액 관련 오류
    INVALID_BETTING_PRICE("[ERROR] 배팅 금액은 양의 정수로만 입력하여 주십시오.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}