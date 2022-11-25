package domain.rule;

public enum GameRule {

    POINT_BLACKJACK(21),
    PARTICIPANTS_MIN_COUNT(1),
    PARTICIPANTS_MAX_COUNT(7),
    INITIAL_BETTING_MIN_VALUE(5_000),
    INITIAL_BETTING_MAX_VALUE(10_000_000);

    private final int value;

    GameRule(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
