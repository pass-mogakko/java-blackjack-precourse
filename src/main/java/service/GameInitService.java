package service;

import domain.card.Card;
import domain.card.CardFactory;
import domain.card.RandomCards;
import domain.user.BettingMoney;
import domain.user.Dealer;
import domain.user.Players;
import domain.user.PlayersName;
import dto.CardsDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameInitService {

    private static final GameInitService gameInitService = new GameInitService();
    private static final Players players = new Players();
    private static final Dealer dealer = new Dealer();
    private static RandomCards randomCards;

    private GameInitService() {
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

    public CardsDto initCards() {
        List<Card> cards = new ArrayList<>(CardFactory.create());
        Collections.shuffle(cards);
        randomCards = RandomCards.newInstance(cards);
        List<String> dealerHasCard = dealer.initCards();
        List<List<String>> playersHasCard = players.initCards();
        return new CardsDto(dealerHasCard, playersHasCard);
    }
}
