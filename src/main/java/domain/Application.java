package domain;

import domain.View.InputView;
import domain.View.OutputView;
import domain.user.Dealer;
import domain.user.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class Application {
    private static InputView inputView = new InputView();
    private static OutputView outputView = new OutputView();

    private static Player createPlayer(String playerName) {
        double bettingMoney = inputView.readBettingMoney(playerName);

        return new Player(playerName, bettingMoney);
    }

    private static List<Player> createPlayers() {
        List<String> playersName = inputView.readPlayers();
        List<Player> players = new ArrayList<>(playersName.size());

        for (String playerName : playersName) {
            Player player = createPlayer(playerName);
            players.add(player);
        }

        return Collections.unmodifiableList(players);
    }

    private static void giveFirstCards(Dealer dealer, List<Player> players) {
        dealer.giveCardToSelf();
        outputView.printDealerCards(dealer.getCards());
        dealer.giveCardToSelf();

        for (Player player : players) {
            giveFirstCardsToPlayer(dealer, player);
        }
    }

    private static void giveFirstCardsToPlayer (Dealer dealer, Player player) {
        dealer.giveCardToPlayer(player);
        dealer.giveCardToPlayer(player);
        outputView.printPlayerCards(player.getCards(), player.getName());
    }

    public static void main(String[] args) {
        Dealer dealer = new Dealer();
        List<Player> players = createPlayers();

        giveFirstCards(dealer, players);
    }
}
