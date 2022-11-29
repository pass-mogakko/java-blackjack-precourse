package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import util.TotalCalculator;
import util.ResultCalculator;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player implements Participants {
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

    public void addCards(List<Card> addCards) {
        cards.addAll(addCards);
    }

    public boolean isSameName(String name) {
        return this.name
                .equals(name);
    }

    public boolean isBlackJack() {
        return ResultCalculator.isBlackJack(cards);
    }

    @Override
    public int getTotal() {
        return (int) TotalCalculator.calculate(cards);
    }

    public String getName() {
        return this.name;
    }

    public double getBettingMoney() {
        return this.bettingMoney;
    }

    public List<Card> getCards() {
        return this.cards;
    }

    @Override
    public boolean equals(Object obj) {
        Player anotherPlayer = (Player) obj;
        return this.name.equals(anotherPlayer.getName());
    }

    // TODO 추가 기능 구현

}
