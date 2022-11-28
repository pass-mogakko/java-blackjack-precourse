package view;

import static view.resource.Format.LIST_DELIMITER;
import static view.resource.OutputContent.FORMAT_OPEN_DEALER_CARDS;
import static view.resource.OutputContent.FORMAT_OPEN_PLAYER_CARDS;
import static view.resource.OutputContent.FORMAT_OPEN_RESULT;

import domain.card.Card;
import view.resource.SymbolDisplay;
import view.resource.TypeDisplay;

import java.util.List;
import java.util.stream.Collectors;

public class CardDisplayConverter {

    public static String makeUserCardsDisplay(String userName, List<Card> cards) {
        if (userName.equals("딜러")) {
            return String.format(FORMAT_OPEN_DEALER_CARDS.getValue(), makeCardsDisplay(cards));
        }
        return String.format(FORMAT_OPEN_PLAYER_CARDS.getValue(), userName, makeCardsDisplay(cards));
    }

    public static String makeUserCardsWithResultDisplay(String userName, List<Card> cards, int score) {
        String scoreDisplay = String.format(FORMAT_OPEN_RESULT.getValue(), score);
        return makeUserCardsDisplay(userName, cards) + scoreDisplay;
    }

    private static String makeCardsDisplay(List<Card> cards) {
        List<String> displays = cards.stream()
                .map(CardDisplayConverter::makeCardDisplay)
                .collect(Collectors.toList());
        return String.join(LIST_DELIMITER.getValue(), displays);
    }

    private static String makeCardDisplay(Card card) {
        String symbolDisplay = SymbolDisplay.findBySymbolName(card.getSymbolName());
        String typeDisplay = TypeDisplay.findByName(card.getTypeName());
        return symbolDisplay + typeDisplay;
    }
}
