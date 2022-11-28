package domain.rule;

public enum ScoreRule {

    SCORE_WINNING(21),
    SCORE_DEALER_ADD_LIMIT(17);

    private final int value;

    ScoreRule(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
