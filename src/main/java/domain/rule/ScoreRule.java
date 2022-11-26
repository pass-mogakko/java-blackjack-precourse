package domain.rule;

public enum ScoreRule {

    POINT_BLACKJACK(21);

    private final int value;

    ScoreRule(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
