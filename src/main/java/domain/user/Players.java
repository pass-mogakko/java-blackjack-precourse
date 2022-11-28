package domain.user;

import domain.card.Deck;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        List<String> playerNames = players.stream()
                .map(player -> player.getName())
                .collect(Collectors.toList());
        return String.join(", ",playerNames);
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public void receiveCards(Deck deck) {
        players.stream()
                .forEach(player -> deck.handOut(player));
    }

}

