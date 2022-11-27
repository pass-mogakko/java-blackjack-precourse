package model;

import static domain.rule.ScoreRule.SCORE_DEALER_ADD_LIMIT;

import domain.card.CardDistributor;
import domain.user.Dealer;
import domain.user.Player;
import model.validator.GameRuleValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private final CardDistributor distributor;
    private final List<Player> players;
    private final Dealer dealer;
    private final Earnings earnings;

    public BlackJackGame(CardDistributor distributor, Map<String, Double> playersToEnroll) {
        this.distributor = distributor;
        this.dealer = new Dealer();
        this.players = new ArrayList<>();
        initializePlayers(playersToEnroll);
        this.earnings = initializeEarnings(playersToEnroll);
    }

    private void initializePlayers(Map<String, Double> playersToEnroll) {
        playersToEnroll.forEach((name, bettingMoney) -> players.add(makePlayer(name, bettingMoney)));
    }

    private Player makePlayer(String name, double bettingMoney) {
        GameRuleValidator.validateEnrolledCount(players.size());
        Player player = new Player(name, bettingMoney);
        GameRuleValidator.validateDuplicatedPlayer(players.contains(player));
        return player;
    }

    private Earnings initializeEarnings(Map<String, Double> playersToEnroll) {
        List<String> playersName = new ArrayList<>(playersToEnroll.keySet());
        return new Earnings(playersName);
    }

    // TODO List<Player> 일급콜렉션으로 리팩토링
    private Player findPlayerByName(String playerName) {
        return players.stream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> {throw new IllegalArgumentException("해당 이름을 가진 사용자가 존재하지 않습니다.");});
    }

    public void addCardToPlayer(String playerName) {
        findPlayerByName(playerName).addCard(distributor.takeOneCard());
    }

    public boolean addCardToDealerIfValid() {
        int score = dealer.addAllScore();
        if (score < SCORE_DEALER_ADD_LIMIT.getValue()) {
            dealer.addCard(distributor.takeOneCard());
            return true;
        }
        return false;
    }

    public void distributeCardsForStart() {
        dealer.addCard(distributor.takeOneCard());
        dealer.addCard(distributor.takeOneCard());
        for (Player player : players) {
            addCardToPlayer(player.getName());
            addCardToPlayer(player.getName());
        }
    }

    public OpenedCardsDto openAllUserCards(boolean showAllDealerCards) {
        if (!showAllDealerCards) {
            Dealer cardHiddenDealer = new Dealer();
            cardHiddenDealer.addCard(dealer.getCards().get(0));
            return new OpenedCardsDto(cardHiddenDealer, players);
        }
        return new OpenedCardsDto(dealer, players);
    }

    public OpenedCardsDto openPlayerCards(String playerName) {
        return new OpenedCardsDto(null, List.of(findPlayerByName(playerName)));
    }
}
