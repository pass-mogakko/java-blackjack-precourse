package domain.View;

import domain.Util.Calculator;
import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;

import java.util.List;
import java.util.stream.Collectors;



public class OutputView {
    private static final String DELIMITER = ",";
    private static final String DEALER_CARDS = "딜러: %s" + System.lineSeparator();
    private static final String PLAYER_CARDS = "%s카드: %s" + System.lineSeparator();
    private static final String DEALER_RESULT = "딜러 카드: %s - 결과: %d" + System.lineSeparator();
    private static final String PLAYER_RESULT = "%s카드: %s - 결과: %d" + System.lineSeparator();
    private static final String DEALER_PROFIT = "딜러: %.0f" + System.lineSeparator();
    private static final String PLAYER_PROFIT = "%s: %.0f" + System.lineSeparator();
    private static final String DEALER_HIT = "딜러는 16이하라 한장의 카드를 더 받았습니다." + System.lineSeparator();



    public void printDealerCards(List<Card> cards) {
        System.out.printf(DEALER_CARDS, cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(DELIMITER))
        );
    }

    public void printPlayerCards(List<Card> cards, String name) {
        System.out.printf(PLAYER_CARDS,
                name,
                cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(DELIMITER))
        );
    }

    public void printDealerResultCards(List<Card> cards) {
        System.out.printf(DEALER_RESULT,
                cards.stream()
                        .map(Card::toString)
                        .collect(Collectors.joining(DELIMITER)),
                Calculator.calculateCards(cards)
        );
    }
    public void printPlayerResultCards(List<Card> cards, String name) {
        System.out.printf(PLAYER_RESULT,
                name,
                cards.stream()
                        .map(Card::toString)
                        .collect(Collectors.joining(DELIMITER)),
                Calculator.calculateCards(cards)
        );
    }

    public void printResultCards(Dealer dealer, List<Player> players) {
        printDealerResultCards(dealer.getCards());
        for (Player player : players) {
            printPlayerResultCards(player.getCards(), player.getName());
        }
    }

    public void printProfits(Dealer dealer, List<Player> players) {
        System.out.println("##최종 수익");

        System.out.printf(DEALER_PROFIT, dealer.calculateProfit(players));
        for (Player player : players) {
            System.out.printf(PLAYER_PROFIT, player.getName(), player.calculateProfit(dealer));
        }
    }

    public void printDealerHit() {
        System.out.println(DEALER_HIT);
    }

    public void printBust(boolean bust) {
        if (bust) {
            System.out.println("Bust!");
        }
        System.out.println();
    }
}
