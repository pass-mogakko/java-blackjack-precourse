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

    public String getPlayerCards() {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(", "));
    }

    public List<Card> getCardsInList(){
        return cards;
    }

    // TODO 추가 기능 구현

}
