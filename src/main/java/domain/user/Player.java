package domain.user;

import domain.card.Card;
import domain.card.Cards;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {

    private final String name;
    private final double bettingMoney;
    private Cards cards = new Cards();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void addCard(Card card) {
        cards.addCard(card);
    }

    // TODO 추가 기능 구현

    public List<String> collectCardToString() {
        return cards.collectCardToString();
    }

}
