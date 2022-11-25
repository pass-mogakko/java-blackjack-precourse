package domain.card;

public enum Symbol {
    ACE(1, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K");

    private int score;
    private String korean;

    Symbol(int score, String korean) {
        this.score = score;
        this.korean = korean;
    }

    public int getScore() {
        return score;
    }

    public String getKorean() {
        return korean;
    }

    public boolean isAce() {
        return this == ACE;
    }
}
