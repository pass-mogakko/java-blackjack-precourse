package service;

import domain.user.BettingMoney;
import domain.user.PlayersName;
import dto.BettingMoneyDto;
import dto.PlayersNameDto;

public class BlackjackService {

    private static final BlackjackService blackJackService = new BlackjackService();

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
}
