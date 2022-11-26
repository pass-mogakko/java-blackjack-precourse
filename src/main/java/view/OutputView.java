package view;

import domain.dto.CardValueDto;
import domain.dto.PlayerNameDto;

public class OutputView {
    private static final String FIRST_ANNOUNCEMENT_MESSAGE_FRONT = "딜러와 ";
    private static final String FIRST_ANNOUNCEMENT_MESSAGE_BACK = "에게 2장씩 나누었습니다.";

    public void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public void printFirstCards(PlayerNameDto playerInfo, CardValueDto cardInfo) {
        System.out.println(FIRST_ANNOUNCEMENT_MESSAGE_FRONT
                + playerInfo.getNameValues() + FIRST_ANNOUNCEMENT_MESSAGE_BACK);
    }
}