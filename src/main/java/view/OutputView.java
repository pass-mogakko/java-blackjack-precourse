package view;

import domain.dto.CardValueDto;
import domain.dto.PlayerNameDto;

import java.util.List;

public class OutputView {
    private static final String FIRST_ANNOUNCEMENT_MESSAGE_FRONT = "딜러와 ";
    private static final String FIRST_ANNOUNCEMENT_MESSAGE_BACK = "에게 2장씩 나누었습니다.";
    private static final String DEALER_CARD_INFO_MESSAGE = "딜러카드: ";
    private static final String PLAYER_CARD_INFO_MESSAGE = "카드: ";

    public void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public void printFirstCards(PlayerNameDto playerInfo, CardValueDto cardInfo) {
        System.out.println();
        System.out.println(FIRST_ANNOUNCEMENT_MESSAGE_FRONT + playerInfo.getNameValues() + FIRST_ANNOUNCEMENT_MESSAGE_BACK);
        System.out.println(DEALER_CARD_INFO_MESSAGE + cardInfo.getDealerCards());

        List<String> playerNames = playerInfo.getNames();
        for (String playerName : playerNames) {
            System.out.println(playerName + PLAYER_CARD_INFO_MESSAGE + cardInfo.getPlayerCards().get(playerName));
        }
    }
}