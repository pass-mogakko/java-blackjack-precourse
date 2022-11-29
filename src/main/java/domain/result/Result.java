package domain.result;

import domain.status.GameResult;
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

    public void putResults(List<Player> players, GameResult gameResult) {
        players.stream()
                .forEach(player -> result.put(player, gameResult));
    }

    public void putBlackJackPlayersResult(List<Player> players) {
        players.stream()
                .forEach(player -> result.put(player, GameResult.BLACKJACK));
    }

    public boolean hasResultByPlayer(Player player) {
        return result.containsKey(player);
    }

    public Map<Player, GameResult> getResult() {
        return this.result;
    }

}
