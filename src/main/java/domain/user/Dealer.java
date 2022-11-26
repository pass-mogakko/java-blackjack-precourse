package domain.user;

import domain.card.RandomCards;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User {

    private static RandomCards randomCards;

    public Dealer() {
    }

    // TODO 추가 기능 구현

    public List<String> initCards() {
        randomCards = RandomCards.getInstance();
        addCard(randomCards.drawCard());
        List<String> dealerHasCards = cards.collectCardToString();
        addCard(randomCards.drawCard());
        return dealerHasCards;
    }

    @Override
    public boolean isPossibleDrawCard() {
        return cards.isLessThanOrEqualTo16();
    }
}
