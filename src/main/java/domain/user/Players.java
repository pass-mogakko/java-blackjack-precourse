package domain.user;

import constant.ErrorMessage;
import domain.card.RandomCards;
import dto.PlayerBenefitResultDto;
import dto.PlayerCardsToStringDto;
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

    public List<PlayerCardsToStringDto> initCards() {
        players.forEach(this::drawTwoCards);
        return collectPlayersCardsToString();
    }

    private void drawTwoCards(Player player) {
        randomCards = RandomCards.getInstance();
        player.addCard(randomCards.drawCard());
        player.addCard(randomCards.drawCard());
    }

    public List<PlayerCardsToStringDto> collectPlayersCardsToString() {
        return players.stream()
                .map(this::createPlayerCardsToStringDto)
                .collect(Collectors.toList());
    }

    private PlayerCardsToStringDto createPlayerCardsToStringDto(Player player) {
        String name = player.getName();
        List<String> playerHasCards = player.collectCardToString();
        int score = player.computeScore();
        return new PlayerCardsToStringDto(name, playerHasCards, score);
    }

    public void drawCard(String playerName) {
        Player player = findPlayerByName(playerName);
        player.addCard(randomCards.drawCard());
    }

    private Player findPlayerByName(String name) {
        return players.stream()
                .filter(player -> player.isSameName(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.NON_EXISTENT_PLAYER_NAME));
    }

    public PlayerCardsToStringDto collectCardToStringByPlayerName(String playerName) {
        Player player = findPlayerByName(playerName);
        String name = player.getName();
        List<String> playerHasCards = player.collectCardToString();
        boolean isPossibleDrawCard = player.isPossibleDrawCard();
        return new PlayerCardsToStringDto(name, playerHasCards, isPossibleDrawCard);
    }

    public List<PlayerBenefitResultDto> computePlayersBenefitResult(Dealer dealer) {
        return players.stream()
                .map(player -> createPlayerBenefitResultDto(player, dealer))
                .collect(Collectors.toList());
    }

    private PlayerBenefitResultDto createPlayerBenefitResultDto(Player player, Dealer dealer) {
        String name = player.getName();
        int benefit = (int) player.computeBenefit(dealer);
        return new PlayerBenefitResultDto(name, benefit);
    }

    public int sumPlayersBenefit(Dealer dealer) {
        return players.stream()
                .map(player -> player.computeBenefit(dealer))
                .mapToInt(Double::intValue)
                .sum();
    }

}
