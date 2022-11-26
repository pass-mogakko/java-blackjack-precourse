package domain.rule;

public enum ParticipantsRule {

    PARTICIPANTS_MIN_COUNT(1),
    PARTICIPANTS_MAX_COUNT(7);

    private final int value;

    ParticipantsRule(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
