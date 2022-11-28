package model;

import static domain.rule.ScoreRule.SCORE_DEALER_ADD_LIMIT;

import domain.card.Card;
import domain.card.CardDistributor;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import model.dto.Earnings;
import model.dto.OpenedCards;
import model.dto.Opening;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackGame {

    private final CardDistributor distributor;
    private final Players players;
    private final Dealer dealer;
    private final EarningCalculator earningCalculator;

    public BlackJackGame(CardDistributor distributor, Map<String, Double> playersToEnroll) {
        this.distributor = distributor;
        this.dealer = new Dealer();
        this.players = new Players(playersToEnroll);
        this.earningCalculator = new EarningCalculator(new ArrayList<>(playersToEnroll.keySet()));
    }

    public void deal() {
        dealer.addCard(distributor.takeOneCard());
        dealer.addCard(distributor.takeOneCard());
        players.filterByStatus(player -> true)
                .forEach(player -> {
                    player.addCard(distributor.takeOneCard());
                    player.addCard(distributor.takeOneCard());
                });
    }

    public Opening open(boolean showAllDealerCards) {
        List<Card> dealerCards = dealer.getCards();
        if (!showAllDealerCards) {
            dealerCards = List.of(dealerCards.get(0));
        }
        return new Opening(new OpenedCards("딜러", dealerCards, dealer.addAllScore()), players.openAllCards());
    }

    public void hitPlayer(String playerName) {
        players.addCardByName(playerName, distributor.takeOneCard());
    }

    public boolean hitDealer() {
        int score = dealer.addAllScore();
        if (score < SCORE_DEALER_ADD_LIMIT.getValue()) {
            dealer.addCard(distributor.takeOneCard());
            return true;
        }
        return false;
    }

    public boolean isDealerBlackJack() {
        return dealer.isBlackJack();
    }

    public List<String> getNotBlackJackPlayersName() {
        return players.filterByStatus(player -> !player.isBlackJack())
                .stream().map(Player::getName)
                .collect(Collectors.toList());
    }

    public OpenedCards openPlayerCards(String playerName) {
        return players.openCardsByName(playerName);
    }

    public void updateEarningsByBlackJack() {
        earningCalculator.calculateEarningsByBlackJack(players, dealer);
    }

    public void updateEarningsWithOutBlackJack() {
        earningCalculator.calculateEarningsWithoutBlackJack(players, dealer);
    }

    public Earnings getEarnings() {
        return earningCalculator.getEarnings();
    }
}
