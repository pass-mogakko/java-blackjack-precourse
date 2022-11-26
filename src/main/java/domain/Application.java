package domain;

import domain.View.InputView;
import domain.user.Dealer;
import domain.user.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class Application {
    private static InputView inputView = new InputView();

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

    public static void main(String[] args) {
        List<Player> players = createPlayers();
        Dealer dealer = new Dealer();
    }
}
