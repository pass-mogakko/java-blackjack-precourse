package util;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static util.ErrorCode.*;

public class Converter {

    Validator validator = new Validator();

    public List<String> convertToNames(Object input) {
        try {
            String str = String.valueOf(input);
            validator.validateStringNotBlank(str);
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(INVALID_PLAYER_NAMES_TYPE.getMessage());
        }
        List<String> names = List.of(String.valueOf(input).split(","));
        return names;
    }

}
