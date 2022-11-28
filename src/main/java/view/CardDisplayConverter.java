package view;

import static view.resource.Format.LIST_DELIMITER;
import static view.resource.OutputContent.FORMAT_OPEN_DEALER_CARDS;
import static view.resource.OutputContent.FORMAT_OPEN_PLAYER_CARDS;
import static view.resource.OutputContent.FORMAT_OPEN_RESULT;

import domain.card.Card;
import model.dto.OpenedCards;
import view.resource.SymbolDisplay;
import view.resource.TypeDisplay;

import java.util.List;
import java.util.stream.Collectors;

public class CardDisplayConverter {

    public static String makeUserCardsWithResultDisplay(OpenedCards openedCards) {
        String scoreDisplay = String.format(FORMAT_OPEN_RESULT.getValue(), openedCards.getScore());
        return makeUserCardsDisplay(openedCards) + scoreDisplay;
    }

    public static String makeUserCardsDisplay(OpenedCards openedCards) {
        if (openedCards.getName().equals("딜러")) {
            return String.format(FORMAT_OPEN_DEALER_CARDS.getValue(), makeCardsDisplay(openedCards.getCards()));
        }
        return String.format(FORMAT_OPEN_PLAYER_CARDS.getValue(), openedCards.getName(),
                makeCardsDisplay(openedCards.getCards()));
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
