package domain.rule;

public enum BettingRule {

    INITIAL_MIN_VALUE(5_000),
    INITIAL_MAX_VALUE(10_000_000);

    private final double value;

    BettingRule(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
