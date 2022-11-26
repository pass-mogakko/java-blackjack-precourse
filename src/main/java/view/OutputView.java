package view;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import view.constant.Constant;
import view.constant.Message;

public class OutputView {

    public void printDrawTwoCard(List<String> playersName, List<List<String>> playersHasCards, List<String> dealerHasCards) {
        String parsedPlayersName = parsePlayersName(playersName);
        String parsedDealerHasCards = parseCards(dealerHasCards);

        printDrawTwoCardEveryone(parsedPlayersName);
        printDealerHasCards(parsedDealerHasCards);
        printPlayersHasCards(playersName, playersHasCards);
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
        System.out.printf(Message.DRAW_TWO_CARDS_EVERYONE, playersName);
    }

    private void printDealerHasCards(String dealerHasCards) {
        System.out.println();
        System.out.printf(Message.DEALER_CARDS, dealerHasCards);
    }

    private void printPlayersHasCards(List<String> playersName, List<List<String>> playersHasCards) {
        System.out.println();
        IntStream.range(Constant.INITIAL_INDEX, playersName.size())
                .forEach(index -> printPlayerHasCards(playersHasCards, playersName, index));
    }

    private void printPlayerHasCards(List<List<String>> playersHasCards, List<String> playersName, int index) {
        List<String> playerHasCards = playersHasCards.get(index);
        String parsedPlayerHasCards = parseCards(playerHasCards);
        System.out.printf(Message.PLAYER_CARDS, playersName.get(index), parsedPlayerHasCards);
        System.out.println();
    }

    public void printPlayerHasCards(List<String> playerHasCards, String playerName) {
        String parsedPlayerHasCards = parseCards(playerHasCards);
        System.out.printf(Message.PLAYER_CARDS, playerName, parsedPlayerHasCards);
        System.out.println();
    }

    public void printDealerDrawCard() {
        System.out.println(Message.DEALER_DRAW_CARD);
    }

    public void printDealerCardsResult(List<String> dealerHasCards, int CardsScore) {
        String parsedDealerHasCards = parseCards(dealerHasCards);
        System.out.println();
        System.out.printf(Message.DEALER_CARDS_RESULT, parsedDealerHasCards, CardsScore);
        System.out.println();
    }

    public void printPlayersCardsResult(List<String> playersName, List<List<String>> playersHasCards, List<Integer> playersScore) {
        for (int i = 0; i < playersName.size(); i++) {
            printPlayerCardsResult(playersName.get(i), playersHasCards.get(i), playersScore.get(i));
        }
    }

    private void printPlayerCardsResult(String playerName, List<String> playerHasCards, int CardsScore) {
        String parsedPlayerHasCards = parseCards(playerHasCards);
        System.out.printf(Message.PLAYER_CARDS_RESULT, playerName, parsedPlayerHasCards, CardsScore);
        System.out.println();
    }
}
