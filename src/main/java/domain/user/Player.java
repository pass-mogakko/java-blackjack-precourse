package domain.user;

import domain.Util.Calculator;
import domain.Util.Discriminator;
import domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public boolean isBust() {
        if (Discriminator.isBust(cards)) {

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

    public int getScore() {
        return Calculator.calculateCards(cards);
    }

    public double calculateProfit(Dealer dealer) {
        if (isBlackjack() && !dealer.isBlackjack()) {
            return bettingMoney * 1.5;
        }

        if (isBust() ||
                (!dealer.isBust() && getScore() < dealer.getScore())) {
            return -bettingMoney;
        }

        if (dealer.isBust() || getScore() > dealer.getScore()) {
            return bettingMoney;
        }

        return 0;
    }

    public double getBettingMoney() {
        return bettingMoney;
    }

    public String getName() {
        return name;
    }
}
