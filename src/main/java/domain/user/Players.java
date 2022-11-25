package domain.user;

import domain.card.RandomCards;
import java.util.ArrayList;
import java.util.List;

public class Players {

    private static final List<Player> players = new ArrayList<>();
    private static RandomCards randomCards;


    public Players() {
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void initCards() {
        players.forEach(player -> drawTwoCards(randomCards, player));
    }

    private void drawTwoCards(RandomCards randomCards, Player player) {
        randomCards = RandomCards.getInstance();
        player.addCard(randomCards.drawCard());
        player.addCard(randomCards.drawCard());
    }
}
