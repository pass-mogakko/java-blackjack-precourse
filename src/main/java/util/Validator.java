package util;

import java.util.List;

import static util.ErrorCode.*;

public class Validator {

    public void validateStringNotBlank(String str) {
        if (str.equals("")) {
            throw new IllegalArgumentException(BLANK_PLAYER_NAMES.getMessage());
        }
    }

    public void validatePlayerNames(List<String> names) {
        if (names.size() == 0) {
            throw new IllegalArgumentException(INVALID_PLAYER_NAMES_TYPE.getMessage());
        }
        for (String name : names) {
            if (name.replace(" ", "").equals("")) {
                throw new IllegalArgumentException(BLANK_PLAYER_NAMES.getMessage());
            }
        }
    }

}
