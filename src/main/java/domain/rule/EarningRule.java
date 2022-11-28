package domain.rule;

public enum EarningRule {

    PUSH(0),
    WIN(1),
    LOSE(-1),
    BLACKJACK_WIN(1.5);

    private final double rate;

    EarningRule(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}
