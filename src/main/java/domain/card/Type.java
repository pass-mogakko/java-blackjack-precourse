package domain.card;

public enum Type {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLUB("클로버");

    private final String korean;

    Type(String korean) {
        this.korean = korean;
    }

    public String getKorean() {
        return korean;
    }
}
