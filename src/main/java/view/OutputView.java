package view;

import static view.resource.Format.LIST_DELIMITER;
import static view.resource.OutputContent.FORMAT_EARNING;

import domain.card.Card;
import view.resource.OutputContent;
import view.validator.IOValidator;

import java.util.List;

public class OutputView {

    public void printMessage(OutputContent outputMessage) {
        ConsolePrinter.printLine(outputMessage.getValue());
    }

    public void printFormattedMessage(OutputContent formattedMessage, String content) {
        ConsolePrinter.printFormattedLine(formattedMessage, content);
    }

    public void printFormattedMessage(OutputContent formattedMessage, List<String> playerNames) {
        ConsolePrinter.printFormattedLine(formattedMessage, String.join(LIST_DELIMITER.getValue(), playerNames));
    }

    public void printOpenedCards(String userName, List<Card> cards) {
        ConsolePrinter.printLine(CardDisplayConverter.makeUserCardsDisplay(userName, cards));
    }

    public void printOpenedCards(String userName, List<Card> cards, int score) {
        ConsolePrinter.printLine(CardDisplayConverter.makeUserCardsWithResultDisplay(userName, cards, score));
    }

    public void printEarning(String name, double earning) {
        ConsolePrinter.printFormattedLine(FORMAT_EARNING, name, earning);
    }

    public void printBlankLine() {
        ConsolePrinter.printLine("");
    }

    private static class ConsolePrinter {

        public static void printFormattedLine(OutputContent format, String content) {
            IOValidator.validateContent(content);
            IOValidator.validateContent(format);
            System.out.printf(format.getValue() + "\n", content);
        }

        public static void printFormattedLine(OutputContent format, String content, double value) {
            IOValidator.validateContent(format);
            IOValidator.validateContent(content);
            System.out.printf(format.getValue() + "\n", content, value);
        }

        public static void printLine(String content) {
            IOValidator.validateContent(content);
            System.out.println(content);
        }
    }
}
