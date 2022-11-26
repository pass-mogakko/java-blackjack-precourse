package view;

import camp.nextstep.edu.missionutils.Console;
import util.Converter;

import java.util.List;

public class InputView {

    private static String REQUEST_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";

    private final Converter converter = new Converter();

    public List<String> readPlayerNames() {
        System.out.println(REQUEST_PLAYER_NAMES_MESSAGE);
        String input = Console.readLine();
        return converter.convertToNames(input);
    }
}
