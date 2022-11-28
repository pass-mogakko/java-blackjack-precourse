package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private double money;
    private final List<Card> cards = new ArrayList<>();
    final double BLACKJACK_MULTIPLE = 1.5;

    public Dealer(double money) {
        this.money = money;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public String getInitialDealerCard() {
        return cards.get(0).toString();
    }

    public String getDealerCards() {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(", "));
    }

    public List<Card> getCardsInList() {
        return cards;
    }

    public boolean isOverThanSixteen() {
        return calculateDealerScore() > 16;
    }

    public int calculateDealerScore() {
        int count = 0;
        for (Card card : cards) {
            count += card.getSymbolScore();
        }
        return count;
    }

    public void giveBackBettingMoney(double bettingMoney) {
        this.money -= bettingMoney;
    }

    public void earnMoney(double bettingMoney) {
        this.money += bettingMoney;
    }

    public void giveBackBlackJack(double bettingMoney) {
        this.money -= bettingMoney * BLACKJACK_MULTIPLE;
    }

    public double getMoney() {
        return money;
    }
    // TODO 추가 기능 구현
}
