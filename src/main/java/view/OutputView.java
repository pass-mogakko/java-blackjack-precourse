package view;

import domain.dto.CardValueDto;
import domain.dto.GameScoreDto;
import domain.dto.PlayerNameDto;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String FIRST_ANNOUNCEMENT_MESSAGE_FRONT = "딜러와 ";
    private static final String FIRST_ANNOUNCEMENT_MESSAGE_BACK = "에게 2장씩 나누었습니다.";
    private static final String DEALER_CARD_INFO_MESSAGE = "딜러카드: ";
    private static final String PLAYER_CARD_INFO_MESSAGE = "카드: ";
    private static final String SCORE_RESULT_INFO_MESSAGE = " - 결과: ";
    private static final String BLACKJACK_RESULT_MESSAGE = "블랙잭이 되어 게임을 종료합니다.";

    public void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public void printFirstCards(PlayerNameDto playerInfo, CardValueDto cardInfo) {
        System.out.println();
        System.out.println(FIRST_ANNOUNCEMENT_MESSAGE_FRONT + playerInfo.getNameValues() + FIRST_ANNOUNCEMENT_MESSAGE_BACK);
        System.out.println(DEALER_CARD_INFO_MESSAGE + cardInfo.getDealerCards());

        for (String playerName : playerInfo.getNames()) {
            System.out.println(playerName + PLAYER_CARD_INFO_MESSAGE + cardInfo.getPlayerCards().get(playerName));
        }
    }

    public void printBlackjackMessage() {
        System.out.println();
        System.out.println(BLACKJACK_RESULT_MESSAGE);
    }

    public void printGameResult(PlayerNameDto playerNameDto, CardValueDto cardValueDto, GameScoreDto gameScoreDto) {
        System.out.println();
        System.out.println(DEALER_CARD_INFO_MESSAGE + cardValueDto.getDealerCards() + SCORE_RESULT_INFO_MESSAGE + gameScoreDto.getDealerScore());

        for (String playerName : playerNameDto.getNames()) {
            System.out.println(playerName + PLAYER_CARD_INFO_MESSAGE + cardValueDto.getPlayerCards().get(playerName) + SCORE_RESULT_INFO_MESSAGE + gameScoreDto.getPlayerScore(playerName));
        }
    }
}