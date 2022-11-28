package domain.user;

import domain.card.Card;
import model.dto.OpenedCards;
import model.validator.GameRuleValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players = new ArrayList<>();

    public Players(Map<String, Double> playersToEnroll) {
        enrollPlayers(playersToEnroll);
    }

    private void enrollPlayers(Map<String, Double> playersToEnroll) {
        playersToEnroll.forEach((name, bettingMoney) -> players.add(makePlayer(name, bettingMoney)));
    }

    private Player makePlayer(String name, double bettingMoney) {
        GameRuleValidator.validateEnrolledCount(players.size());
        Player player = new Player(name, bettingMoney);
        GameRuleValidator.validateDuplicatedPlayer(players.contains(player));
        return player;
    }

    private Player findPlayerByName(String playerName) {
        return players.stream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> {throw new IllegalArgumentException("해당 이름을 가진 사용자가 존재하지 않습니다.");});
    }

    public void addCardByName(String name, Card card) {
        findPlayerByName(name).addCard(card);
    }

    public List<Player> filterByStatus(Predicate<Player> playerStatus) {
        return players.stream()
                .filter(playerStatus)
                .collect(Collectors.toList());
    }

    public OpenedCards openCardsByName(String name) {
        Player player = findPlayerByName(name);
        return new OpenedCards(name, player.getCards(), player.addAllScore());
    }

    public List<OpenedCards> openAllCards() {
        List<OpenedCards> cards = new ArrayList<>();
        players.forEach(player -> cards.add(new OpenedCards(player.getName(), player.getCards(), player.addAllScore())));
        return cards;
    }
}
