package view.resource;

public enum OutputContent {

    MESSAGE_ASK_PLAYERS_NAME("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"),
    FORMAT_ASK_BETTING_MONEY("%s의 베팅 금액은?");

    private final String value;

    OutputContent(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
