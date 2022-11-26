package service;

import domain.card.Card;
import domain.card.CardFactory;
import domain.card.RandomCards;
import domain.user.BettingMoney;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import domain.user.PlayersName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackjackService {

    private static final BlackjackService playerService = new BlackjackService();
    private static final Players players = new Players();
    private static Dealer dealer = new Dealer();
    private static RandomCards randomCards;

    private BlackjackService() {
        List<Card> cards = new ArrayList<>(CardFactory.create());
        Collections.shuffle(cards);
        randomCards = RandomCards.newInstance(cards);
    }

    public static BlackjackService getInstance() {
        return playerService;
    }

    public List<List<String>> initPlayersCards() {
        return players.initCards();
    }

    public void createPlayer(String name, int bettingMoney) {
        Player player = new Player(name, bettingMoney);
        players.addPlayer(player);
    }

    public List<String> drawPlayerCard(String playerName) {
        players.drawCard(playerName);
        return findPlayerHasCards(playerName);
    }

    public List<String> findPlayerHasCards(String playerName) {
        return players.collectCardToStringByPlayerName(playerName);
    }

    public boolean isPlayerPossibleDrawCard(String playerName) {
        return players.isPossibleDrawCard(playerName);
    }

    public List<List<String>> collectPlayersCardsToString() {
        return players.collectPlayersCardsToString();
    }

    public List<Integer> collectScore() {
        return players.collectScore();
    }


    public List<String> parsePlayersName(String playersName) {
        PlayersName parsedPlayersName = new PlayersName(playersName);
        return parsedPlayersName.get();
    }

    public int parsePlayerBettingMoney(String bettingMoney) {
        BettingMoney money = new BettingMoney(bettingMoney);
        return money.get();
    }


    public List<String> initDealerCards() {
        return dealer.initCards();
    }

    public boolean isDealerPossibleDrawCard() {
        return dealer.isPossibleDrawCard();
    }

    public void drawDealerCard() {
        dealer.addCard(randomCards.drawCard());
    }

    public List<String> findDealerHasCards() {
        return dealer.collectCardToString();
    }

    public int computeDealerScore() {
        return dealer.computeScore();
    }
}
