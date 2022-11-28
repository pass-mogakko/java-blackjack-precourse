import domain.BlackJackGame;
import domain.user.Dealer;
import domain.user.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static view.InputView.*;
import static view.OutputView.printHandOutCards;

public class Application {
    private static final int INIT_DEALER_MONEY = 0;

    public static void main(String[] args) {
        List<Player> players = getPlayersList(getPlayerName());
        Dealer dealer = new Dealer(INIT_DEALER_MONEY);
        BlackJackGame blackJackGame = new BlackJackGame(players, dealer);

        run(blackJackGame);
    }

    private static void run(BlackJackGame game) {
        game.handOutFirstTwoCards();
        printHandOutCards(game);
        for(Player player: game.getPlayers()){
            game.askYesOrNo(player);
        }
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
