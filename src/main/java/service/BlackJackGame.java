package service;

import domain.Result;
import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import dto.UsersDTO;
import java.util.List;

public class BlackJackGame {

    private final Dealer dealer;
    private final Players players;
    private final Deck deck;
    private final Result result;

    public BlackJackGame(Dealer dealer, Players players, Deck deck) {
        this.dealer = dealer;
        this.players = players;
        this.deck = deck;
        this.result = new Result();
    }

    public void divideFirstTime() {
        dealer.addCards(deck.handOutFirstTime());
        players.addCardsFirstTime(deck);
    }

    public void divideMoreCardOfPlayer(String name) {
        players.handOutMoreCardByName(name,deck.handOutMoreCard());
    }

    public void divideMoreCardToDealer() {
        if (dealer.getTotal() < 17) {
            dealer.addCard(deck.handOutMoreCard());
        }
    }

    public void addBlackJackResult() {
        result.putBlackJackPlayersResult(players.getBlackJackPlayers());
    }

    public void addResult(List<Player> players) {
        players.stream()
                .forEach(player -> result.addCompareResult(dealer, player));
    }

    public boolean isMoreCardToDealer() {
        return dealer.getTotal() < 17;
    }

    public UsersDTO getUsers() {
        return new UsersDTO(dealer, players);
    }

    public List<Player> getNotBlackJackPlayers() {
        return players.getNotBlackJackPlayers();
    }

    public boolean isDealerBlackJack() {
        return dealer.isBlackJack();
    }

    public Result getResult() {
        return this.result;
    }

    public Players getPlayers() {
        return this.players;
    }

}
