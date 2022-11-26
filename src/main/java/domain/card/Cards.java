package domain.card;

import constant.Constant;
import constant.GameResult;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private final List<Card> cards;

    public Cards() {
        cards = new ArrayList<>();
    }

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public List<String> collectCardToString() {
        return cards.stream()
                .map(Card::cardToString)
                .collect(Collectors.toList());
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int computeScore() {
        int cardsScore = sumCardsScoreRegardlessAce();
        int aceCount = countAce();

        while (isNeedScoreAdjustment(cardsScore, aceCount)) {
            aceCount--;
            cardsScore -= Constant.ADJUSTMENT_ACE_SCORE;
        }
        return cardsScore;
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private int sumCardsScoreRegardlessAce() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean isNeedScoreAdjustment(int cardsScore, int aceCount) {
        return cardsScore > Constant.BLACKJACK_MAX_SCORE && aceCount > 0;
    }

    public boolean isLessThanMaxScore() {
        return computeScore() < Constant.BLACKJACK_MAX_SCORE;
    }

    public boolean isLessThanOrEqualToDealerDrawCardStandardScore() {
        return computeScore() <= Constant.DEALER_DRAW_CARD_STANDARD_SCORE;
    }

    public boolean isBurst() {
        return computeScore() > Constant.BLACKJACK_MAX_SCORE;
    }

    private boolean isBlackjack() {
        return computeScore() == Constant.BLACKJACK_MAX_SCORE && isSizeTwo();
    }

    public boolean isSizeTwo() {
        return cards.size() == 2;
    }

    public GameResult computeGameResult(Cards dealerCards) {
        if (!isBlackjack() && !dealerCards.isBlackjack()) {
            return computeGameResultWithoutBlackjack(dealerCards);
        }
        return computeGameResultWithBlackjack(dealerCards);
    }

    private GameResult computeGameResultWithoutBlackjack(Cards dealerCards) {
        if (!isBurst() && !dealerCards.isBurst()) {
            return computeGameResultWithoutBurst(dealerCards);
        }
        return computeGameResultWithBurst();
    }

    private GameResult computeGameResultWithoutBurst(Cards dealerCards) {
        int playerScore = computeScore();
        int dealerScore = dealerCards.computeScore();
        if (playerScore > dealerScore) {
            return GameResult.WIN;
        }
        if (playerScore < dealerScore) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    private GameResult computeGameResultWithBurst() {
        if (isBurst()) {
            return GameResult.LOSE;
        }
        return GameResult.WIN;
    }

    private GameResult computeGameResultWithBlackjack(Cards dealerCards) {
        if (isBlackjack() && dealerCards.isBlackjack()) {
            return GameResult.DRAW;
        }
        if (isBlackjack()) {
            return GameResult.BLACKJACK;
        }
        return GameResult.LOSE;
    }
}
