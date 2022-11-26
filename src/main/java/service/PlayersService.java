package service;

import domain.user.Player;
import domain.user.Players;
import java.util.List;

public class PlayersService {

    private static final PlayersService playerService = new PlayersService();
    private static final Players players = new Players();

    private PlayersService() {

    }

    public static PlayersService getInstance() {
        return playerService;
    }

    public List<List<String>> initCards() {
        return players.initCards();
    }

    public void createPlayer(String name, int bettingMoney) {
        Player player = new Player(name, bettingMoney);
        players.addPlayer(player);
    }

    public List<String> drawCard(String playerName) {
        players.drawCard(playerName);
        return findPlayerHasCards(playerName);
    }

    public List<String> findPlayerHasCards(String playerName) {
        return players.collectCardToStringByPlayerName(playerName);
    }

    public boolean isPossibleDrawCard(String playerName) {
        return players.isPossibleDrawCard(playerName);
    }
}
