package model;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

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

    public void calculateEarningsByBlackJack(List<Player> players, Dealer dealer) {
        if (dealer.isBlackJack()) {
            calculateEarningAsLose(players, player -> !player.isBlackJack());
            return;
        }
        calculateEarningAsBlackJackWin(players, User::isBlackJack);
    }

    public void calculateEarningsWithoutBlackJack(List<Player> players, Dealer dealer) {
        if (dealer.isBust()) {
            calculateEarningAsWin(players, player -> !player.isBust());
            return;
        }
        calculateEarningAsLose(players, player -> toLoseWithoutBlackJack(player, dealer.addAllScore()));
        calculateEarningAsWin(players, player -> toWinWithoutBlackJack(player, dealer.addAllScore()));
    }

    private boolean toLoseWithoutBlackJack(Player player, double dealerScore) {
        if (player.isBust()) {
            return true;
        }
        return player.addAllScore() < dealerScore;
    }

    private boolean toWinWithoutBlackJack(Player player, double dealerScore) {
        if (player.isBlackJack()) {
            return false;
        }
        if (player.isBust()) {
            return false;
        }
        return player.addAllScore() > dealerScore;
    }

    // TODO 메소드 하나로 수정하기. Lose=(-1), BlackJackWin=(1.5), Win=(1)
    private void calculateEarningAsLose(List<Player> players, Predicate<Player> playerStatus) {
        players.stream()
                .filter(playerStatus)
                .forEach(player -> earnings.moveEarningFromDealerToPlayer(
                        player.getName(),(-1)*player.getBettingMoney())
                );
    }

    private void calculateEarningAsBlackJackWin(List<Player> players, Predicate<Player> playerStatus) {
        players.stream()
                .filter(playerStatus)
                .forEach(player -> earnings.moveEarningFromDealerToPlayer(
                        player.getName(),(1.5)*player.getBettingMoney())
                );
    }

    private void calculateEarningAsWin(List<Player> players, Predicate<Player> playerStatus) {
        players.stream()
                .filter(playerStatus)
                .forEach(player -> earnings.moveEarningFromDealerToPlayer(
                        player.getName(),(1)*player.getBettingMoney())
                );
    }
}
