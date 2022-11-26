package domain.View;

import domain.Calculator;
import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



public class OutputView {
    private static final String DEALER_CARDS = "딜러:%s" + System.lineSeparator();
    private static final String PLAYER_CARDS = "%s카드:%s" + System.lineSeparator();
    private static final String DEALER_RESULT = "딜러 카드:%s - 결과: %d" + System.lineSeparator();
    private static final String PLAYER_RESULT = "%s카드:%s - 결과: %d" + System.lineSeparator();

    public void printDealerCards(List<Card> cards) {
        System.out.printf(DEALER_CARDS, cards.stream().
                map(Card::toString).
                collect(Collectors.joining(","))
        );
    }

    public void printPlayerCards(List<Card> cards, String name) {
        System.out.printf(PLAYER_CARDS,
                name,
                cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(","))
        );
    }

    public void printDealerResultCards(List<Card> cards) {
        System.out.printf(DEALER_RESULT,
                cards.stream()
                        .map(Card::toString)
                        .collect(Collectors.joining(",")),
                Calculator.calculateCards(cards)
        );
    }
    public void printPlayerResultCards(List<Card> cards, String name) {
        System.out.printf(PLAYER_RESULT,
                name,
                cards.stream()
                        .map(Card::toString)
                        .collect(Collectors.joining(",")),
                Calculator.calculateCards(cards)
        );
    }

    public void printResultCards(Dealer dealer, List<Player> players) {
        printDealerResultCards(dealer.getCards());
        for (Player player : players) {
            printPlayerResultCards(player.getCards(), player.getName());
        }
    }
}
