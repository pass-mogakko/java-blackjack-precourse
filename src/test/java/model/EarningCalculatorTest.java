package model;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.user.Dealer;
import domain.user.Players;
import model.dto.Earnings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class EarningCalculatorTest {

    private final List<String> playerNames = List.of("pobi", "jason", "joon");
    private EarningCalculator earningCalculator;

    @BeforeEach
    void initializeCalculator() {
        earningCalculator = new EarningCalculator(playerNames);
    }

    @DisplayName("블랙잭 여부에 따라 다르게 수익 계산")
    @ParameterizedTest
    @MethodSource("generateStreamOfCards")
    void calculateEarningsByBlackJack(List<Card> dealerCards, List<List<Card>> playerCards) {
        Dealer dealer = new Dealer();
        dealerCards.forEach(dealer::addCard);
        Players players = setPlayers(playerCards);

        earningCalculator.calculateEarningsByBlackJack(players, dealer);
        Earnings earnings = earningCalculator.getEarnings();

        assertThat(earnings.getDealerEarning()).isEqualTo(30_000);
        assertThat(earnings.findPlayerEarningByName("pobi").getEarning()).isEqualTo(0);
        assertThat(earnings.findPlayerEarningByName("jason").getEarning()).isEqualTo(-20_000);
        assertThat(earnings.findPlayerEarningByName("joon").getEarning()).isEqualTo(-10_000);
    }

    private static Stream<Arguments> generateStreamOfCards() {
        List<Card> dealerCards = List.of(new Card(Symbol.QUEEN, Type.CLUB), new Card(Symbol.ACE, Type.SPADE));
        List<List<Card>> playerCards = List.of(
                // pobi (blackjack)
                List.of(new Card(Symbol.ACE, Type.HEART), new Card(Symbol.KING, Type.DIAMOND)),
                // jason (win/lose)
                List.of(new Card(Symbol.NINE, Type.HEART), new Card(Symbol.KING, Type.HEART)),
                // joon (bust)
                List.of(new Card(Symbol.JACK, Type.DIAMOND), new Card(Symbol.TWO, Type.SPADE),
                        new Card(Symbol.QUEEN, Type.HEART))
        );
        return Stream.of(Arguments.of(dealerCards, playerCards));
    }

    @DisplayName("각 플레이어의 푸쉬(동점), 승리, 패배, 버스트에 따라 다르게 수익 계산")
    @ParameterizedTest
    @MethodSource("generateStreamOfCardsWithoutBlackJack")
    void calculateEarningsWithoutBlackJack(List<Card> dealerCards, List<List<Card>> playerCards) {
        Dealer dealer = new Dealer();
        dealerCards.forEach(dealer::addCard);
        Players players = setPlayers(playerCards);

        earningCalculator.calculateEarningsWithoutBlackJack(players, dealer);
        Earnings earnings = earningCalculator.getEarnings();

        assertThat(earnings.getDealerEarning()).isEqualTo(20_000);
        assertThat(earnings.findPlayerEarningByName("pobi").getEarning()).isEqualTo(10_000);
        assertThat(earnings.findPlayerEarningByName("jason").getEarning()).isEqualTo(-20_000);
        assertThat(earnings.findPlayerEarningByName("joon").getEarning()).isEqualTo(-10_000);
    }


    @DisplayName("딜러가 버스트이면 버스트 제외한 플레이어 모두 베팅 금액만큼 수익 계산")
    @ParameterizedTest
    @MethodSource("generateStreamOfCardsWithoutBlackJack")
    void calculateEarningsWithDealerBust(List<Card> dealerCards, List<List<Card>> playerCards) {
        Dealer dealer = new Dealer();
        dealerCards.forEach(dealer::addCard);
        dealer.addCard(new Card(Symbol.TEN, Type.HEART));
        Players players = setPlayers(playerCards);

        earningCalculator.calculateEarningsWithoutBlackJack(players, dealer);
        Earnings earnings = earningCalculator.getEarnings();

        assertThat(earnings.getDealerEarning()).isEqualTo(-30_000);
        assertThat(earnings.findPlayerEarningByName("pobi").getEarning()).isEqualTo(10_000);
        assertThat(earnings.findPlayerEarningByName("jason").getEarning()).isEqualTo(20_000);
        assertThat(earnings.findPlayerEarningByName("joon").getEarning()).isEqualTo(0);
    }

    private static Stream<Arguments> generateStreamOfCardsWithoutBlackJack() {
        List<Card> dealerCards = List.of(new Card(Symbol.QUEEN, Type.CLUB), new Card(Symbol.SEVEN, Type.SPADE));
        List<List<Card>> playerCards = List.of(
                // pobi (win)
                List.of(new Card(Symbol.EIGHT, Type.HEART), new Card(Symbol.KING, Type.DIAMOND)),
                // jason (lose)
                List.of(new Card(Symbol.SIX, Type.HEART), new Card(Symbol.KING, Type.HEART)),
                // joon (bust)
                List.of(new Card(Symbol.JACK, Type.DIAMOND), new Card(Symbol.TWO, Type.SPADE),
                        new Card(Symbol.QUEEN, Type.HEART))
        );
        return Stream.of(Arguments.of(dealerCards, playerCards));
    }

    private Players setPlayers(List<List<Card>> playerCards) {
        Map<String, Double> playersToEnroll = new LinkedHashMap<>(
                Map.of("pobi", 10_000.0, "jason", 20_000.0, "joon", 10_000.0)
        );
        Players players = new Players(playersToEnroll);
        for (int index = 0; index < playerCards.size(); index++) {
            List<Card> playerCard = playerCards.get(index);
            String playerName = playerNames.get(index);
            playerCard.forEach(card -> players.addCardByName(playerName, card));
        }
        return players;
    }
}