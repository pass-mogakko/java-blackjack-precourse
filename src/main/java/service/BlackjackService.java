package service;

import domain.user.PlayersName;
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
}
