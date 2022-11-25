package domain.user;

import domain.card.RandomCards;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private static final List<Player> players = new ArrayList<>();
    private static RandomCards randomCards;


    public Players() {
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<List<String>> initCards() {
        players.forEach(player -> drawTwoCards(player));
        return collectAllPlayerCardsToString();
    }

    private void drawTwoCards(Player player) {
        randomCards = RandomCards.getInstance();
        player.addCard(randomCards.drawCard());
        player.addCard(randomCards.drawCard());
    }

    private List<List<String>> collectAllPlayerCardsToString() {
        return players.stream()
                .map(Player::collectCardToString)
                .collect(Collectors.toList());
    }
}
