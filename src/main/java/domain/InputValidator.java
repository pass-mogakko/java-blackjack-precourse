package domain;

import com.sun.scenario.effect.impl.sw.sse.SSEEffectPeer;
import java.util.Arrays;



public class InputValidator {
    private final static String SEPARATOR = ",";
    private final static String STRING_REGEX = "^[a-zA-z]+";

    public static void validatePlayersInput(String input) throws IllegalArgumentException {
        String players = input.replaceAll(SEPARATOR, "");

        if (!players.matches(STRING_REGEX)) {
            throw new IllegalArgumentException("잘못된 입력");
        }
    }
}
