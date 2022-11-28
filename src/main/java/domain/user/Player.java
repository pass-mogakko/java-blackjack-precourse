package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();
    private double money = 0;
    final int INIT_CARDS_COUNT = 2;
    final double INIT_BLACKJACK_MULTIPLE = 2;
    final double BLACKJACK_MULTIPLE = 1.5;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public String getName() {
        return this.name;
    }

    public double getBettingMoney() {
        return this.bettingMoney;
    }

    public String getPlayerCards() {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(", "));
    }

    public List<Card> getCardsInList() {
        return cards;
    }

    public int calculatePlayerScore() {
        int count = 0;
        for (Card card : cards) {
            count += card.getSymbolScore();
        }
        return count;
    }

    public boolean isTwoCards() {
        return cards.size() == INIT_CARDS_COUNT;
    }

    public void loseAll() {
        money -= bettingMoney;
    }

    public void initBlackJack() {
        money += bettingMoney * BLACKJACK_MULTIPLE;
    }

    public void blackJack() {
        money += bettingMoney * INIT_BLACKJACK_MULTIPLE;
    }

    public void earnBettingMoney() {
        money += bettingMoney;
    }

    public double getPlayerMoney() {
        return money;
    }

    // TODO 추가 기능 구현

}
