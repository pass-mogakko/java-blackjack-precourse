package view;

import java.util.List;
import java.util.stream.Collectors;
import view.constant.Constant;
import view.constant.Message;

public class OutputView {

    public void printDrawTwoCardsEveryone(List<String> playersName) {
        String parsedPlayersName = parsePlayersName(playersName);
        System.out.println();
        System.out.printf(Message.DRAW_TWO_CARDS_EVERYONE, Message.DEALER, parsedPlayersName);
        System.out.println();
    }

    private String parsePlayersName(List<String> playersName) {
        return playersName.stream()
                .collect(Collectors.joining(Constant.PLAYERS_NAME_JOINING_DELIMITER));
    }

    public void printDealerHasFirstCard(String dealerHasFirstCard) {
        System.out.printf(Message.DEALER_CARDS, dealerHasFirstCard);
    }
}
