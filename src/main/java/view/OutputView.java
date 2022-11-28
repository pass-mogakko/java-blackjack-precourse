package view;

import domain.card.Card;
import domain.user.Players;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DEALER = "딜러";

    public void printCards(HoldingInfo holdingInfo) {
        System.out.println();
    }

    public void printHandOut(List<Players> users) {
        System.out.println(DEALER + "와 " + users + "에게 2장의 카드를 나누었습니다.");
    }

    public void printHoldingCards(List<Card> cards) {
        List<String> cardTexts = cards.stream()
                .map(card -> card.getType().getTypeKoreanName() + card.getSymbol().getScore())
                .collect(Collectors.toList());
        System.out.println(String.join(", ",cardTexts));
    }

}
