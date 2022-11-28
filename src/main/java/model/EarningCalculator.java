package model;

import static domain.rule.EarningRule.BLACKJACK_WIN;
import static domain.rule.EarningRule.LOSE;
import static domain.rule.EarningRule.WIN;

import domain.rule.EarningRule;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import domain.user.User;
import model.dto.Earnings;

import java.util.List;
import java.util.function.Predicate;

public class EarningCalculator {

    private final Earnings earnings;

    public EarningCalculator(List<String> playerNames) {
        this.earnings = new Earnings(playerNames);
    }

    public Earnings getEarnings() {
        return earnings;
    }

    public void calculateEarningsByBlackJack(Players players, Dealer dealer) {
        if (dealer.isBlackJack()) {
            calculateEarning(players, player -> !player.isBlackJack(), LOSE);
            return;
        }
        calculateEarning(players, User::isBlackJack, BLACKJACK_WIN);
    }

    public void calculateEarningsWithoutBlackJack(Players players, Dealer dealer) {
        if (dealer.isBust()) {
            calculateEarning(players, player -> !player.isBust(), WIN);
            return;
        }
        calculateEarning(players, player -> toLoseWithoutBlackJack(player, dealer.getScore()), LOSE);
        calculateEarning(players, player -> toWinWithoutBlackJack(player, dealer.getScore()), WIN);
    }

    private void calculateEarning(Players players, Predicate<Player> playerStatus, EarningRule earningRule) {
        players.filterByStatus(playerStatus)
                .forEach(player -> earnings.moveEarningFromDealerToPlayer(
                        player.getName(),(earningRule.getRate())*player.getBettingMoney())
                );
    }

    private boolean toLoseWithoutBlackJack(Player player, double dealerScore) {
        if (player.isBust()) {
            return true;
        }
        return player.getScore() < dealerScore;
    }

    private boolean toWinWithoutBlackJack(Player player, double dealerScore) {
        if (player.isBlackJack()) {
            return false;
        }
        if (player.isBust()) {
            return false;
        }
        return player.getScore() > dealerScore;
    }
}
