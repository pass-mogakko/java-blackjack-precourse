package view.resource;

public enum OutputContent {

    MESSAGE_ASK_PLAYERS_NAME("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"),
    MESSAGE_INFORM_DEALER_HIT("딜러는 16 이하라 한 장의 카드를 더 받았습니다."),

    FORMAT_ASK_BETTING_MONEY("%s의 베팅 금액은?"),
    FORMAT_INFORM_DISTRIBUTED("딜러와 %s에게 2장의 카드를 나누었습니다."),
    FORMAT_ASK_HIT_OR_STAY("%s는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"),

    FORMAT_OPEN_DEALER_CARDS("딜러: %s"),
    FORMAT_OPEN_PLAYER_CARDS("%s카드: %s"),
    FORMAT_OPEN_RESULT(" - 결과: %d"),

    FORMAT_DEALER_EARNING("딜러: %.0f"),
    FORMAT_PLAYER_EARNING("%s: %.0f");

    private final String value;

    OutputContent(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
