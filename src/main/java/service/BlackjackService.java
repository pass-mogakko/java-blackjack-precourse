package service;

import domain.card.Card;
import domain.card.CardFactory;
import domain.card.RandomCards;
import domain.dto.BenefitResultDto;
import domain.dto.CardsToStringDto;
import domain.dto.PlayerBenefitResultDto;
import domain.dto.PlayerCardsToStringDto;
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

    public List<String> parsePlayersName(String playersName) {
        PlayersName parsedPlayersName = new PlayersName(playersName);
        return parsedPlayersName.get();
    }

    public void createPlayer(String name, String bettingMoney) {
        BettingMoney money = new BettingMoney(bettingMoney);
        Player player = new Player(name, money.get());
        players.addPlayer(player);
    }

    public CardsToStringDto initCards() {
        List<PlayerCardsToStringDto> playerCardsToStringDtos = players.initCards();
        List<String> dealerHasCards = dealer.initCards();
        return new CardsToStringDto(playerCardsToStringDtos, dealerHasCards);
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

    public List<PlayerCardsToStringDto> collectPlayersCardsToString() {
        return players.collectPlayersCardsToString();
    }

    public List<Integer> collectScore() {
        return players.collectScore();
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

    public BenefitResultDto computeBenefit() {
        List<PlayerBenefitResultDto> playerBenefitResultDtos = players.computePlayersBenefitResult(dealer);
        int dealerBenefit = -players.sumPlayersBenefit(dealer);
        return new BenefitResultDto(dealerBenefit, playerBenefitResultDtos);
    }

}
