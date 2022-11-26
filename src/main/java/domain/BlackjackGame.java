package domain;

import domain.card.Card;
import domain.card.CardFactory;
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
}
