package view;

import static view.resource.Format.LIST_DELIMITER;
import static view.resource.OutputContent.FORMAT_EARNING;

import model.dto.Earning;
import model.dto.Earnings;
import model.dto.OpenedCards;
import model.dto.Opening;
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

    public void printOpening(Opening opening) {
        printOpenedCards(opening.getDealerCards());
        for (OpenedCards playerCards : opening.getPlayerCards()) {
            printOpenedCards(playerCards);
        }
    }

    public void printResult(Opening opening) {
        printOpenedCardsWithResult(opening.getDealerCards());
        for (OpenedCards playerCards : opening.getPlayerCards()) {
            printOpenedCardsWithResult(playerCards);
        }
    }

    public void printOpenedCards(OpenedCards openedCards) {
        ConsolePrinter.printLine(CardDisplayConverter.makeUserCardsDisplay(openedCards));
    }

    private void printOpenedCardsWithResult(OpenedCards openedCards) {
        ConsolePrinter.printLine(CardDisplayConverter.makeUserCardsWithResultDisplay(openedCards));
    }

    public void printEarnings(Earnings earnings) {
        printEarning(earnings.getDealerEarning());
        earnings.getPlayerEarnings()
                .forEach(this::printEarning);
    }

    private void printEarning(Earning earning) {
        ConsolePrinter.printFormattedLine(FORMAT_EARNING, earning.getName(), earning.getValue());
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
