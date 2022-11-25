package repository;

import domain.user.Player;
import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> players = new ArrayList<>();

    public Players() {
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
}
