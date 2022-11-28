package model.dto;

import domain.card.Card;

import java.util.List;

public class OpenedCards {

    private final String name;
    private final List<Card> cards;
    private final int score;

    public OpenedCards(String name, List<Card> cards, int score) {
        this.name = name;
        this.cards = cards;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }
}
