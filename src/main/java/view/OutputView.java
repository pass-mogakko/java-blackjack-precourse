package view;

import domain.BlackJackGame;
import domain.user.Dealer;
import domain.user.Player;

import java.util.List;

public class OutputView {
    private static final String DEALER = "딜러";
    private static final String COLON = ": ";
    private static final String CARD = "카드";
    private static final String AND = "와 ";
    private static final String HANDOUT_CARDS_MESSAGE = "에게 2장의 카드를 나누었습니다.";

    public static void printHandOutCards(BlackJackGame game) {
        System.out.print(System.lineSeparator());
        printHandOutMessage(game.getPlayers());
        printDealerCard(game.getDealer());
        printPlayersCard(game.getPlayers());
    }

    private static void printHandOutMessage(List<Player> players) {
        System.out.print(DEALER + AND);
        System.out.print(printPlayerNames(players));
        System.out.println(HANDOUT_CARDS_MESSAGE);
    }

    private static StringBuilder printPlayerNames(List<Player> players) {
        StringBuilder playerNames = new StringBuilder();
        for (Player player : players) {
            playerNames.append(player.getName()).append(", ");
        }
        playerNames.deleteCharAt(playerNames.lastIndexOf(" "));
        playerNames.deleteCharAt(playerNames.lastIndexOf(","));
        return playerNames;
    }

    private static void printDealerCard(Dealer dealer) {
        System.out.print(DEALER + COLON);
        System.out.println(dealer.getInitialDealerCard());
    }

    private static void printPlayersCard(List<Player> players) {
        for (Player player : players) {
            printPlayerCard(player);
        }
    }

    public static void printPlayerCard(Player player) {
        System.out.print(player.getName() + CARD + COLON);
        System.out.println(player.getPlayerCards());
    }
}
