package domain.rule;

public enum ScoreRule {

    POINT_BLACKJACK(21),
    POINT_DEALER_ADD_LIMIT(17);

    private final int value;

    ScoreRule(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
