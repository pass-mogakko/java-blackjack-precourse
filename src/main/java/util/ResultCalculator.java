package util;

import domain.GameResult;
import domain.card.Card;
import java.util.List;

public class ResultCalculator {

    public static GameResult compare(List<Card> dealerCards, List<Card> playerCards) {
        double dealerPoint = TotalCalculator.calculateTotal(dealerCards);
        double playerPoint = TotalCalculator.calculate(playerCards);
        if ((dealerPoint < playerPoint && (playerPoint < 21)) || dealerPoint > 21) {
            return GameResult.WIN;
        }
        if (dealerPoint > playerPoint || playerPoint > 21) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    public static boolean isBlackJack(List<Card> cards) {
        return cards.size() == 2 && TotalCalculator.calculate(cards) == 21;
    }


}
