package view;

import static view.resource.Format.LIST_DELIMITER;
import static view.resource.OutputContent.FORMAT_DEALER_EARNING;
import static view.resource.OutputContent.FORMAT_OPEN_DEALER_CARDS;
import static view.resource.OutputContent.FORMAT_OPEN_PLAYER_CARDS;
import static view.resource.OutputContent.FORMAT_OPEN_RESULT;
import static view.resource.OutputContent.FORMAT_PLAYER_EARNING;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import model.Earnings;
import model.OpenedCardsDto;
import view.resource.OutputContent;
import view.resource.SymbolDisplay;
import view.resource.TypeDisplay;

import java.util.List;
import java.util.stream.Collectors;

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

    public void printOpenedCards(OpenedCardsDto openedCards) {
        Dealer dealer = openedCards.getDealer();
        if (dealer != null) {
            ConsolePrinter.printLine(makeUserCardsDisplay(dealer));
        }
        for (Player player : openedCards.getPlayers()) {
            ConsolePrinter.printLine(makeUserCardsDisplay(player));
        }
    }

    public void printOpenedCardsWithResult(OpenedCardsDto openedCards) {
        Dealer dealer = openedCards.getDealer();
        if (dealer != null) {
            ConsolePrinter.printLine(makeUserCardsWithResultDisplay(dealer, dealer.addAllScore()));
        }
        for (Player player : openedCards.getPlayers()) {
            ConsolePrinter.printLine(makeUserCardsWithResultDisplay(player, player.addAllScore()));
        }
    }

    // TODO 변환 로직 클래스 분리
    private String makeUserCardsWithResultDisplay(User user, int score) {
        String scoreDisplay = String.format(FORMAT_OPEN_RESULT.getValue(), score);
        return makeUserCardsDisplay(user) + scoreDisplay;
    }

    private String makeUserCardsDisplay(User user) {
        if (user.getClass() == Player.class) {
            Player player = (Player) user;
            return String.format(FORMAT_OPEN_PLAYER_CARDS.getValue(), player.getName(),makeCardsDisplay(player.getCards()));
        }
        return String.format(FORMAT_OPEN_DEALER_CARDS.getValue(), makeCardsDisplay(user.getCards()));
    }

    private String makeCardsDisplay(List<Card> cards) {
        List<String> displays = cards.stream()
                .map(this::makeCardDisplay)
                .collect(Collectors.toList());
        return String.join(LIST_DELIMITER.getValue(), displays);
    }

    private String makeCardDisplay(Card card) {
        String symbolDisplay = SymbolDisplay.findBySymbolName(card.getSymbolName());
        String typeDisplay = TypeDisplay.findByName(card.getTypeName());
        return symbolDisplay + typeDisplay;
    }

    public void printEarnings(Earnings earnings, List<String> playerNames) {
        ConsolePrinter.printFormattedLine(FORMAT_DEALER_EARNING, earnings.getDealerEarning());
        for (String playerName : playerNames) {
            ConsolePrinter.printFormattedLine(FORMAT_PLAYER_EARNING, playerName, earnings.findPlayerEarningByName(playerName));
        }
    }

    public void printBlankLine() {
        ConsolePrinter.printLine("");
    }

    private static class ConsolePrinter {

        public static void printFormattedLine(OutputContent format, String content) {
            validateContent(content);
            validateContent(format);
            System.out.printf(format.getValue() + "\n", content);
        }

        public static void printFormattedLine(OutputContent format, String content, double value) {
            validateContent(format);
            validateContent(content);
            System.out.printf(format.getValue() + "\n", content, value);
        }

        public static void printFormattedLine(OutputContent format, double value) {
            validateContent(format);
            System.out.printf(format.getValue() + "\n", value);
        }

        public static void printLine(String content) {
            validateContent(content);
            System.out.println(content);
        }

        private static void validateContent(Object content) {
            if (content == null) {
                throw new IllegalArgumentException("출력할 정보의 값이 null입니다.");
            }
        }
    }
}
