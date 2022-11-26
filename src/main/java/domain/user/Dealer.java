package domain.user;

import domain.card.Card;
import util.Calculator;
import util.Converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private final List<Card> cards = new ArrayList<>();

    private final Converter converter = new Converter();
    private final Calculator calculator = new Calculator();

    public Dealer() {}

    public void addCard(Card card) {
        cards.add(card);
    }

    // TODO 추가 기능 구현

    public void shuffleCards(List<Card> cardDeck) {
        Collections.shuffle(cardDeck);
    }

    public Card giveTopCard(List<Card> cardDeck) {
        Card nextCard = cardDeck.get(0);
        cardDeck.remove(0);
        return nextCard;
    }

    public void giveCardsToPlayers(List<Player> players, List<Card> cardDeck) {
        for (Player player : players) {
            for (int i = 0; i < 2; i++) {
                Card card = giveTopCard(cardDeck);
                player.addCard(card);
            }
        }
    }

    public void giveCardsToDealer(Dealer dealer, List<Card> cardDeck) {
        for (int i = 0; i < 2; i++) {
            Card card = giveTopCard(cardDeck);
            dealer.addCard(card);
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public String getCardValues() {
        List<String> cardValues = new ArrayList<>();
        for (Card card : cards) {
            cardValues.add(card.getCardValue());
        }
        return converter.convertListToStirng(cardValues);
    }

    public boolean isBlackjack() {
        return calculator.addAllCardScore(cards) == 21;
    }
}
