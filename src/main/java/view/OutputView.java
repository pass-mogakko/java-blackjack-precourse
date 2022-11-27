package view;

import domain.user.Dealer;
import domain.user.Player;

import java.util.List;

public class OutputView {
    private static final String DEALER = "딜러";
    private static final String COLON = ":";
    private static final String CARD = "카드";
    private static final String AND = "와 ";
    private static final String HANDOUT_CARDS_MESSAGE = "에게 2장의 카드를 나누었습니다.";

    public void printHandOutMessage(List<String> playerName) {
        System.out.print(DEALER + AND);
        System.out.print(String.join(", ", playerName));
        System.out.println(HANDOUT_CARDS_MESSAGE);
    }

    public void printDealerCard(Dealer dealer) {
        System.out.print(DEALER + COLON);
        System.out.println(dealer.getInitialDealerCard());
    }

    public void printPlayerCard(Player player) {
        System.out.print(player.getName() + CARD + COLON);
        System.out.println(player.getPlayerCards());
    }
}
