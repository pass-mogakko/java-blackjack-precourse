package domain.user;

import domain.Calculator;
import domain.card.Card;

import domain.card.CardFactory;
import domain.card.Deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private static final int HIT_LOWER_LIMIT = 16;

    private final List<Card> cards = new ArrayList<>();

    public Dealer() {}

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        if (Calculator.calculateCards(cards) > 21) {
            return true;
        }

        return false;
    }

    public boolean isShouldHit() {
        if (Calculator.calculateCards(cards) <= HIT_LOWER_LIMIT) {
            return true;
        }

        return false;
    }

    public boolean isBlackjack() {
        return Calculator.isBlackjack(cards);
    }
    public int getScore() {
        return Calculator.calculateCards(cards);
    }

    private double calculatePerProfit(Player player) {
        if (!isBlackjack() && player.isBlackjack()) {
            return -(player.getBettingMoney() * 1.5);
        }

        if (player.isBust() ||
                (!isBust() && player.getScore() < getScore())) {
            return player.getBettingMoney();
        }

        if (isBust() || player.getScore() > getScore()) {
            return -player.getBettingMoney();
        }

        return 0;
    }

    public double calculateProfit(List<Player> players) {
        double profit = 0;

        for (Player player : players) {
            profit += calculatePerProfit(player);
        }

        return profit;
    }

    //getter
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
