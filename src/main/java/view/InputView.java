package view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String REQUEST_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String REQUEST_PLAYER_BATTING_MESSAGE = "의 배팅 금액은?";

    public Object readPlayerNames() {
        System.out.println(REQUEST_PLAYER_NAMES_MESSAGE);
        return Console.readLine();
    }

    public Object readBattingPrice(String playerName) {
        System.out.println(playerName + REQUEST_PLAYER_BATTING_MESSAGE);
        return Console.readLine();
    }
}
