package domain;

import domain.user.Dealer;
import domain.user.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {

    private Map<Player, GameResult> result;

    public Result() {
        this.result = new HashMap<>();
    }

    public void putResult(Player player, GameResult gameResult) {
        result.put(player, gameResult);
    }

    public void putBlackJackPlayersResult(List<Player> players) {
        players.stream()
                .forEach(player -> result.put(player, GameResult.BLACKJACK));
    }

    public void addCompareResult(Dealer dealer, Player player) {
        if ((dealer.getTotal() < player.getTotal() && (player.getTotal() < 21)) || dealer.getTotal() > 21) {
            System.out.println(player.getName() + "가 이겼습니다.");
            putResult(player, GameResult.WIN);
            return;
        }

        if (dealer.getTotal() == player.getTotal()) {
            System.out.println(player.getName() + "는 무승부입니다.");
            putResult(player, GameResult.DRAW);
            return;
        }

        if (dealer.getTotal() > player.getTotal() || player.getTotal() > 21) {
            System.out.println(player.getName() + "는 패배했습니다.");
            putResult(player, GameResult.LOSE);
            return;
        }
    }

    public Map<Player, GameResult> getResult() {
        return this.result;
    }

}
