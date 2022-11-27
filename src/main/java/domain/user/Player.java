package domain.user;

import domain.BlackjackGame;
import domain.card.Card;
import domain.dto.GameResultDto;
import util.Calculator;
import util.Converter;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    private final Converter converter = new Converter();
    private final Calculator calculator = new Calculator();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public String getName() {
        return name;
    }

    public double getBettingMoney() {
        return bettingMoney;
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

    public boolean isAffordable() {
        return calculator.addAllCardScore(cards) <= 21;
    }

    public double calculateNormalProfit(GameResultDto gameResult) {
        if (gameResult.getWonPlayers().contains(this) || gameResult.isDealerExceeded()) {
            return bettingMoney;
        }
        return (-1) * bettingMoney;
    }

//    public double calculateBlackjackProfit(BlackjackGame blackjackGame) {
//        if ()
//    }
}
