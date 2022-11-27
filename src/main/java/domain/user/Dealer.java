package domain.user;

import domain.Util.Calculator;
import domain.Util.Discriminator;

import domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class Dealer {
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
        if (Discriminator.isShouldHit(cards)) {
            return true;
        }

        return false;
    }

    public boolean isBlackjack() {
        if (Discriminator.isBlackjack(cards)) {
            return true;
        }

        return false;
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

    public int getScore() {
        return Calculator.calculateCards(cards);
    }
}
