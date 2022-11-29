package util;

import static org.assertj.core.api.Assertions.*;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.calculator.TotalCalculator;

class TotalCalculatorTest {

    public List<Card> getCards(Symbol...symbols) {
        return Arrays.stream(symbols)
                .map(symbol -> new Card(symbol, Type.SPADE))
                .collect(Collectors.toList());
    }

    @DisplayName("[KING,9,A,A] 일 경우 최적의 값으로 21로계산")
    @Test
    void king_nine_A_A_to_21() {
        List<Card> cards = new ArrayList<>(getCards(Symbol.KING, Symbol.NINE, Symbol.ACE, Symbol.ACE));

        double value = TotalCalculator.aceCalculate(cards);
        System.out.println(value);
        assertThat(value).isEqualTo(21);
    }

    @DisplayName("[A,A] 일 경우 최적의 값으로 12로계산")
    @Test
    void A_A_TO_12() {
        List<Card> cards = new ArrayList<>(getCards(Symbol.ACE, Symbol.ACE));

        double value = TotalCalculator.aceCalculate(cards);
        System.out.println(value);
        assertThat(value).isEqualTo(12);
    }


}