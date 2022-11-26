package util;

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
        return convertToPrettyList(input);
    }

    private List<String> convertToPrettyList(Object input) {
        String new_str = String.valueOf(input)
                                .replace(", ", ",")
                                .replace(" ,", ",");
        return List.of(new_str.split(","));
    }

    public double convertToDouble(Object input) {
        try {
            return Double.parseDouble(String.valueOf(input));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_BETTING_PRICE.getMessage());
        }
    }

    public String convertListToStirng(List<String> strs) {
        StringBuilder result = new StringBuilder();
        for (String str : strs) {
            if (!result.toString().equals("")) {
                result.append(", ");
            }
            result.append(str);
        }
        return String.valueOf(result);
    }

    public boolean convertToBoolean(String input) {
        if (input.equals("y") || input.equals("Y")) {
            return true;
        }
        if (input.equals("n") || input.equals("N")) {
            return false;
        }
        throw new IllegalArgumentException(INVALID_COMMAND.getMessage());
    }

}
