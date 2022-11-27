package domain.user;

import domain.card.Card;
import domain.dto.GameResultDto;
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

    public void giveCardsToPlayers(List<Player> players, List<Card> cardDeck, int times) {
        for (Player player : players) {
            for (int i = 0; i < times; i++) {
                giveOneCardToPlayer(player, cardDeck);
            }
        }
    }

    public void giveOneCardToPlayer(Player player, List<Card> cardDeck) {
        Card card = giveTopCard(cardDeck);
        player.addCard(card);
    }

    public void giveCardsToDealer(List<Card> cardDeck, int times) {
        for (int i = 0; i < times; i++) {
            Card card = giveTopCard(cardDeck);
            addCard(card);
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

    public boolean getAdditionalCard(List<Card> cardDeck) {
        if (calculator.addAllCardScore(cards) <= 16) {
            giveCardsToDealer(cardDeck, 1);
            return true;
        }
        return false;
    }

    public double calculateNormalProfit(GameResultDto gameResult, List<Player> players) {
        if (gameResult.isDealerExceeded()) {
            players.removeAll(gameResult.getLostPlayers());
            return getLeftBettingMoney(gameResult, players);
        }
        if (gameResult.isPlayerWon()) {
            return getLeftBettingMoney(gameResult, gameResult.getWonPlayers());
        }
        return gameResult.getTotalBettingMoney();
    }

    private double getLeftBettingMoney(GameResultDto gameResult, List<Player> earnedPlayers) {
        double leftBettingMoney = gameResult.getTotalBettingMoney();
        for (Player player : earnedPlayers) {
            leftBettingMoney -= player.getBettingMoney();
        }
        return leftBettingMoney;
    }
}
