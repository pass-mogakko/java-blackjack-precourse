package domain.user;

import domain.card.Card;
import domain.card.Cards;
import domain.card.RandomCards;
import domain.constant.Constant;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {

    private static RandomCards randomCards;
    private Cards cards = new Cards();

    public Dealer() {
    }

    public void addCard(Card card) {
        cards.addCard(card);
    }

    // TODO 추가 기능 구현

    public String initCards() {
        randomCards = RandomCards.getInstance();
        addCard(randomCards.drawCard());
        addCard(randomCards.drawCard());
        List<String> dealerHasCards = cards.collectCardToString();
        return dealerHasCards.get(Constant.CARDS_FRONT_INDEX);
    }

}
