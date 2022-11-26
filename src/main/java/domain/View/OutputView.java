package domain.View;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



public class OutputView {
    private static final String DEALER_CARDS = "딜러:%s" + System.lineSeparator();
    private static final String PLAYER_CARDS = "%s카드:%s" + System.lineSeparator();
    public void printDealerCards(List<Card> cards) {
        System.out.printf(DEALER_CARDS, cards.stream().
                map(Card::toString).
                collect(Collectors.joining(","))
        );
    }

    public void printPlayerCards(List<Card> cards, String name) {
        System.out.printf(PLAYER_CARDS,
                name,
                cards.stream().
                map(Card::toString).
                collect(Collectors.joining(","))
        );
    }
}
