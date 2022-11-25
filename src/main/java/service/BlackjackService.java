package service;

import domain.user.BettingMoney;
import domain.user.Player;
import domain.user.PlayersName;
import dto.BettingMoneyDto;
import dto.PlayersNameDto;
import repository.Players;

public class BlackjackService {

    private static final BlackjackService blackJackService = new BlackjackService();
    private static final Players players = new Players();

    private BlackjackService() {
    }

    public static BlackjackService getInstance() {
        return blackJackService;
    }

    public PlayersNameDto parsePlayersName(String playersName) {
        PlayersName parsedPlayersName = new PlayersName(playersName);
        return parsedPlayersName.toDto();
    }

    public BettingMoneyDto parsePlayerBettingMoney(String bettingMoney) {
        BettingMoney money = new BettingMoney(bettingMoney);
        return money.toDto();
    }

    public void createPlayer(String name, int bettingMoney) {
        Player player = new Player(name, bettingMoney);
        players.addPlayer(player);
    }
}
