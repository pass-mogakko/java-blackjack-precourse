package service;

import domain.card.Card;
import domain.card.CardFactory;
import domain.card.Cards;
import domain.user.BettingMoney;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import domain.user.PlayersName;
import dto.BettingMoneyDto;
import dto.PlayersNameDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackjackService {

    private static final BlackjackService blackJackService = new BlackjackService();
    private static final Players players = new Players();
    private static final Dealer dealer = new Dealer();
    private static Cards cards;


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

    public void initCards() {
        List<Card> randomCards = new ArrayList<>(CardFactory.create());
        Collections.shuffle(randomCards);
        this.cards = new Cards(randomCards);
        dealer.addCard(cards.drawCard());
        dealer.addCard(cards.drawCard());
    }


}
