package view;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import view.constant.Constant;
import view.constant.Message;

public class OutputView {

    public void printDrawTwoCard(List<String> playersName, List<List<String>> playersHasCard, List<String> dealerHasCard) {
        String parsedPlayersName = parsePlayersName(playersName);
        String parsedDealerHasCard = parseCards(dealerHasCard);

        printDrawTwoCardEveryone(parsedPlayersName);
        printDealerHasCard(parsedDealerHasCard);
        printPlayersHasCard(playersName, playersHasCard);
    }

    private String parsePlayersName(List<String> playersName) {
        return playersName.stream()
                .collect(Collectors.joining(Constant.PLAYERS_NAME_JOINING_DELIMITER));
    }

    private String parseCards(List<String> cards) {
        return cards.stream()
                .collect(Collectors.joining(Constant.CARDS_JOINING_DELIMITER));
    }

    private void printDrawTwoCardEveryone(String playersName) {
        System.out.println();
        System.out.printf(Message.DRAW_TWO_CARDS_EVERYONE, Message.DEALER, playersName);
    }

    private void printDealerHasCard(String dealerHasCard) {
        System.out.println();
        System.out.printf(Message.DEALER_CARDS, dealerHasCard);
    }

    private void printPlayersHasCard(List<String> playersName, List<List<String>> playersHasCard) {
        System.out.println();
        IntStream.range(Constant.INITIAL_INDEX, playersName.size())
                .forEach(index -> printPlayerHasCard(playersHasCard, playersName, index));
    }

    private void printPlayerHasCard(List<List<String>> playersHasCard, List<String> playersName, int index) {
        List<String> playerHasCard = playersHasCard.get(index);
        String parsedPlayerHasCard = parseCards(playerHasCard);
        System.out.printf(Message.PLAYER_CARDS, playersName.get(index), parsedPlayerHasCard);
        System.out.println();
    }

    public void printPlayerHasCard(List<String> playerHasCard, String playerName) {
        String parsedPlayerHasCard = parseCards(playerHasCard);
        System.out.printf(Message.PLAYER_CARDS, playerName, parsedPlayerHasCard);
        System.out.println();
    }

    public void printDealerDrawCard() {
        System.out.println(Message.DEALER_DRAW_CARD);
    }
}
