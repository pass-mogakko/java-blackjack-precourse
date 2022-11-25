package view;

import dto.CardsDto;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import view.constant.Constant;
import view.constant.Message;

public class OutputView {

    public void printDrawTwoCards(List<String> playersName, CardsDto cardsDto) {
        List<String> dealerHasCard = cardsDto.getDealerHasCard();
        List<List<String>> playersHasCard = cardsDto.getPlayersHasCard();
        String parsedPlayersName = parsePlayersName(playersName);
        String parsedDealerHasCard = parseCards(dealerHasCard);

        System.out.println();
        System.out.printf(Message.DRAW_TWO_CARDS_EVERYONE, Message.DEALER, parsedPlayersName);
        System.out.println();
        System.out.printf(Message.DEALER_CARDS, parsedDealerHasCard);
        System.out.println();
        IntStream.range(Constant.INITIAL_INDEX, playersName.size())
                .forEach(index -> {
                    List<String> playerHasCard = playersHasCard.get(index);
                    String parsedPlayerHasCard = parseCards(playerHasCard);
                    System.out.printf(Message.PLAYER_CARDS, playersName.get(index), parsedPlayerHasCard);
                    System.out.println();
                });
    }

    private String parsePlayersName(List<String> playersName) {
        return playersName.stream()
                .collect(Collectors.joining(Constant.PLAYERS_NAME_JOINING_DELIMITER));
    }

    private String parseCards(List<String> cards) {
        return cards.stream()
                .collect(Collectors.joining(Constant.CARDS_JOINING_DELIMITER));
    }
}
