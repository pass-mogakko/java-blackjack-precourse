package view;

import domain.dto.CardValueDto;
import domain.dto.GameProfitDto;
import domain.dto.GameScoreDto;
import domain.dto.PlayerNameDto;

import java.util.List;

public class OutputView {
    private static final String FIRST_ANNOUNCEMENT_MESSAGE_FRONT = "딜러와 ";
    private static final String FIRST_ANNOUNCEMENT_MESSAGE_BACK = "에게 2장씩 나누었습니다.";
    private static final String DEALER = "딜러";
    private static final String CARD_INFO_MESSAGE = "카드: ";
    private static final String SCORE_RESULT_INFO_MESSAGE = " - 결과: ";
    private static final String BLACKJACK_RESULT_MESSAGE = "블랙잭이 되어 게임을 종료합니다.";
    private static final String DEALER_GOT_ADDITIONAL_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String DEALER_DID_NOT_GET_ADDITIONAL_CARD_MESSAGE = "딜러는 17 이상이라 카드를 더 받지 않습니다.";
    private static final String COLON = ": ";
    private static final String GAME_PROFIT_RESULT_MESSAGE = "## 최종 수익";

    public void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public void printFirstCards(PlayerNameDto playerInfo, CardValueDto cardInfo) {
        System.out.println();
        System.out.println(FIRST_ANNOUNCEMENT_MESSAGE_FRONT + playerInfo.getNameValues() + FIRST_ANNOUNCEMENT_MESSAGE_BACK);
        System.out.println(DEALER + CARD_INFO_MESSAGE + cardInfo.getDealerCards());

        List<String> playerNames = playerInfo.getNames();
        for (int i=0; i < playerNames.size(); i++) {
            System.out.println(playerNames.get(i) + CARD_INFO_MESSAGE + cardInfo.getPlayerCard(i));
        }
    }

    public void printBlackjackMessage() {
        System.out.println();
        System.out.println(BLACKJACK_RESULT_MESSAGE);
    }

    public void printGameResult(PlayerNameDto playerNameDto, CardValueDto cardValueDto, GameScoreDto gameScoreDto) {
        System.out.println();
        System.out.println(DEALER + CARD_INFO_MESSAGE + cardValueDto.getDealerCards() + SCORE_RESULT_INFO_MESSAGE + gameScoreDto.getDealerScore());

        List<String> playerNames = playerNameDto.getNames();
        for (int i=0; i < playerNames.size(); i++) {
            System.out.println(playerNames.get(i) + CARD_INFO_MESSAGE + cardValueDto.getPlayerCard(i) + SCORE_RESULT_INFO_MESSAGE + gameScoreDto.getPlayerScore(i));
        }
    }

    public void printPlayerCardValue(String playerName, CardValueDto cardInfo, int idx) {
        System.out.println(playerName + CARD_INFO_MESSAGE + cardInfo.getPlayerCard(idx));
    }

    public void printDealerGotCard() {
        System.out.println();
        System.out.println(DEALER_GOT_ADDITIONAL_CARD_MESSAGE);
    }

    public void printDealerDidNotGetCard() {
        System.out.println();
        System.out.println(DEALER_DID_NOT_GET_ADDITIONAL_CARD_MESSAGE);
    }

    public void printGameProfit(GameProfitDto gameProfitDto, PlayerNameDto playerNameDto) {
        System.out.println();
        System.out.println(GAME_PROFIT_RESULT_MESSAGE);
        System.out.println(DEALER + COLON + gameProfitDto.getDealerProfit());
        List<String> playerNames = playerNameDto.getNames();
        for (int i=0; i < playerNames.size(); i++) {
            System.out.println(playerNames.get(i) + COLON + gameProfitDto.getPlayerProfit(i));
        }
    }
}