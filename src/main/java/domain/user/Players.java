package domain.user;

import domain.card.RandomCards;
import domain.constant.ErrorMessage;
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
        return collectPlayersCardsToString();
    }

    private void drawTwoCards(Player player) {
        randomCards = RandomCards.getInstance();
        player.addCard(randomCards.drawCard());
        player.addCard(randomCards.drawCard());
    }

    public List<List<String>> collectPlayersCardsToString() {
        return players.stream()
                .map(Player::collectCardToString)
                .collect(Collectors.toList());
    }

    public boolean isPossibleDrawCard(String playerName) {
        Player player = findPlayerByName(playerName);
        return player.isPossibleDrawCard();
    }

    private Player findPlayerByName(String name) {
        return players.stream()
                .filter(player -> player.isSameName(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.NON_EXISTENT_PLAYER_NAME));
    }

    public void drawCard(String playerName) {
        Player player = findPlayerByName(playerName);
        player.addCard(randomCards.drawCard());
    }

    public List<String> collectCardToStringByPlayerName(String playerName) {
        Player player = findPlayerByName(playerName);
        return player.collectCardToString();
    }

    public List<Integer> collectScore() {
        return players.stream()
                .map(Player::computeScore)
                .collect(Collectors.toList());
    }
}
