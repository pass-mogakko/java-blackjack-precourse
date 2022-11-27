package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private final double money;
    private final List<Card> cards = new ArrayList<>();

    public Dealer(double money) {
        this.money = money;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public String getInitialDealerCard(){
        return cards.get(0).toString();
    }

    public String getDealerCards(){
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(", "));
    }

    public List<Card> getCardsInList(){
        return cards;
    }

    // TODO 추가 기능 구현
}
