package domain;

import domain.card.Card;
import domain.card.CardFactory;
import domain.dto.BlackjackResultDto;
import domain.dto.GameResultDto;
import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {

    public void start(List<Player> players, Dealer dealer) {
        List<Card> cardDeck = new ArrayList<>(CardFactory.create());
        dealer.shuffleCards(cardDeck);
        dealer.giveCardsToPlayers(players, cardDeck);
        dealer.giveCardsToDealer(dealer, cardDeck);
    }

    public Object play(List<Player> players, Dealer dealer) {
        if (gotBlackjack(players, dealer)) {
            return buildBlackjackResult(players, dealer);
        }
        return null;
    }

    private boolean gotBlackjack(List<Player> players, Dealer dealer) {
        if (dealer.isBlackjack() || playerGotBlackjack(players)) {
            return true;
        }
        return false;
    }

    private BlackjackResultDto buildBlackjackResult(List<Player> players, Dealer dealer) {
        List<Player> blackjackPlayers = findBlackjackPlayers(players);
        boolean dealerBlackjack = dealer.isBlackjack();
        boolean playerBlackjack = blackjackPlayers.size() > 0;
        return new BlackjackResultDto(dealerBlackjack, playerBlackjack, blackjackPlayers);
    }

    private boolean playerGotBlackjack(List<Player> players) {
        return findBlackjackPlayers(players).size() > 0;
    }

    private List<Player> findBlackjackPlayers(List<Player> players) {
        List<Player> blackjackPlayers = new ArrayList<>();
        for (Player player : players) {
            if (player.isBlackjack()) {
                blackjackPlayers.add(player);
            }
        }
        return blackjackPlayers;
    }
}