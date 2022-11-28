package domain.card;

import domain.user.Players;
import domain.user.Participants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {

    private final Stack<Card> deck = new Stack<>();

    public Deck(List<Card> cards) {
        List<Card> newCards = new ArrayList<>(cards);
        Collections.shuffle(newCards);
        newCards.stream()
                .forEach(card -> deck.push(card));
    }

    public void handOut(Participants user) {
        for (int i=0; i<2; i++) {
            user.addCard(deck.pop());
        }
    }

    public void handOutPlayers(Players players) {
        players.receiveCards(this);
    }
}

