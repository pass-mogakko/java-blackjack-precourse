package util.validator;

import constants.ExceptionMessage;


public class Validator {

    public static void validateBettingMoney(String bettingMoney) {
        if (!isNumber(bettingMoney)) {
            throw new IllegalArgumentException(ExceptionMessage.ERROR_INPUT_BETTNG_MONEY_NOT_NUMBER);
        }
    }

    public static void validateCommand(String command) {
        if (!command.equals("y") && !command.equals("n")) {
            throw new IllegalArgumentException(ExceptionMessage.ERROR_INPUT_COMMAND);
        }
    }

    private static boolean isNumber(String bridgeSize) {
        return bridgeSize.chars().allMatch(Character::isDigit);
    }
}
