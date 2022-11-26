package service;

import domain.card.Card;
import domain.card.CardFactory;
import domain.card.RandomCards;
import domain.user.BettingMoney;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import domain.user.PlayersName;
import dto.BettingMoneyDto;
import dto.CardsDto;
import dto.IsPossibleDrawCardDto;
import dto.PlayerHasCardDto;
import dto.PlayersNameDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackjackService {

    private static final BlackjackService blackJackService = new BlackjackService();
    private static final Players players = new Players();
    private static final Dealer dealer = new Dealer();
    private static RandomCards randomCards;

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

    public CardsDto initCards() {
        List<Card> cards = new ArrayList<>(CardFactory.create());
        Collections.shuffle(cards);
        randomCards = RandomCards.newInstance(cards);
        List<String> dealerHasCard = dealer.initCards();
        List<List<String>> playersHasCard = players.initCards();
        return new CardsDto(dealerHasCard, playersHasCard);
    }

    public PlayerHasCardDto drawCard(String playerName) {
        players.drawCard(playerName);
        return findPlayerHasCard(playerName);
    }

    public PlayerHasCardDto findPlayerHasCard(String playerName) {
        List<String> playersHasCard = players.collectCardToStringByPlayerName(playerName);
        return new PlayerHasCardDto(playersHasCard);
    }

    public IsPossibleDrawCardDto isPossibleDrawCard(String playerName) {
        boolean isPossibleDrawCard = players.isPossibleDrawCard(playerName);
        return new IsPossibleDrawCardDto(isPossibleDrawCard);
    }
}
