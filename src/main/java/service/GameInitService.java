package service;

import domain.card.Card;
import domain.card.CardFactory;
import domain.card.RandomCards;
import domain.user.BettingMoney;
import domain.user.PlayersName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameInitService {

    private static final GameInitService gameInitService = new GameInitService();
    private static RandomCards randomCards;

    private GameInitService() {
        List<Card> cards = new ArrayList<>(CardFactory.create());
        Collections.shuffle(cards);
        randomCards = RandomCards.newInstance(cards);
    }

    public static GameInitService getInstance() {
        return gameInitService;
    }

    public List<String> parsePlayersName(String playersName) {
        PlayersName parsedPlayersName = new PlayersName(playersName);
        return parsedPlayersName.get();
    }

    public int parsePlayerBettingMoney(String bettingMoney) {
        BettingMoney money = new BettingMoney(bettingMoney);
        return money.get();
    }
}
