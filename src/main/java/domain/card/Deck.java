package domain.card;

import constants.BlackJack;
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

    public Card handOutMoreCard() {
        return deck.pop();
    }

    public List<Card> handOutFirstTime() {
        List<Card> peekCards = new ArrayList<>();
        for (int i=0; i<BlackJack.CARD_COUNT_FIRST_TIME; i++) {
            peekCards.add(deck.pop());
        }
        return peekCards;
    }

}

