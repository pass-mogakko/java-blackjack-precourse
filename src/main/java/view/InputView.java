package view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String REQUEST_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String REQUEST_PLAYER_BATTING_MESSAGE = "의 배팅 금액은?";
    private static final String REQUEST_COMMAND_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";

    public Object readPlayerNames() {
        System.out.println(REQUEST_PLAYER_NAMES_MESSAGE);
        return Console.readLine();
    }

    public Object readBettingPrice(String playerName) {
        System.out.println();
        System.out.println(playerName + REQUEST_PLAYER_BATTING_MESSAGE);
        return Console.readLine();
    }

    public Object readPlayerCommand(String playerName) {
        System.out.println();
        System.out.println(playerName + REQUEST_COMMAND_MESSAGE);
        return Console.readLine();
    }
}
