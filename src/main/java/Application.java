import domain.user.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static view.InputView.readBettingMoney;
import static view.InputView.readPlayerName;

public class Application {
    public static void main(String[] args) {
        List<Player> players = getPlayersList(getPlayerName());

        //System.out.println(bettingMoney);

    }

    private void run() {

    }

    private static List<String> getPlayerName() {
        try {
            return readPlayerName();
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
            return getPlayerName();
        }
    }

    private static List<Player> getPlayersList(List<String> playerName) {
        List<Player> players = new ArrayList<>();
        for (String name : playerName) {
            players.add(
                    new Player(name, getBettingMoney(name))
            );
        }
        return players;
    }

    private static int getBettingMoney(String name) {
        try {
            return readBettingMoney(name);
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
            return getBettingMoney(name);
        }
    }
}
