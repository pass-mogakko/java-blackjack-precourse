package domain;

import static org.junit.jupiter.api.Assertions.*;

import domain.Util.InputValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;



class InputValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"abc-fg", "fe,.rwd", "a-b-c", "a,", " ", "", "A;"})
    void validateWrongPlayersInput(String input) {
        assertThatThrownBy(() ->
                InputValidator.validatePlayersInput(input))
                .isInstanceOf(IllegalArgumentException.class
                );
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc,fg", "fe,rwd", "a,b,c", "1", "안녕", "a"})
    void validateRightPlayersInput(String input) {
        assertDoesNotThrow(() -> InputValidator.validatePlayersInput(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,3,2,", "1a", "a1"})
    void validateWrongBettingMoney(String input) {
        assertThatThrownBy(() ->
                InputValidator.validateBettingMoney(input))
                .isInstanceOf(IllegalArgumentException.class
                );
    }

    @ParameterizedTest
    @ValueSource(strings = {"132", "11", "30000"})
    void validateRightBettingMoney(String input) {
        assertDoesNotThrow(() -> InputValidator.validateBettingMoney(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"s", "", " "})
    void validateWrongSelection(String input) {
        assertThatThrownBy(() ->
                InputValidator.validateSelection(input))
                .isInstanceOf(IllegalArgumentException.class
                );
    }

    @ParameterizedTest
    @ValueSource(strings = {"y", "n"})
    void validateRightSelection(String input) {
        assertDoesNotThrow(() -> InputValidator.validateSelection(input));
    }
}