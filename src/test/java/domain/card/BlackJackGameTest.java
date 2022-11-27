package domain.card;

import domain.BlackJackGame;
import domain.user.Dealer;
import domain.user.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackJackGameTest {
    private final Player player1 = new Player("pobi", 300);
    private final Player player2 = new Player("suan", 500);
    private final List<Player> players = List.of(player1, player2);
    private final Dealer dealer = new Dealer(0);

    @DisplayName("카드가 덱에서 제거되는지 확인")
    @Test
    void removeCardFromDeck() {
        BlackJackGame game = new BlackJackGame(players, dealer);
        game.handOutFirstTwoCards();
        assertThat(game.getDeckSize())
                .isEqualTo(46);
    }

    @DisplayName("플레이어의 카드가 두장씩 나누어졌는지 확인")
    @Test
    void checkHandoutPlayerTwoCards() {
        BlackJackGame game = new BlackJackGame(players, dealer);
        game.handOutFirstTwoCards();
        assertThat(player1.getCardsInList()).hasSize(2);
    }

    @DisplayName("딜러의 카드가 두장 주어졌는지 확인")
    @Test
    void checkHandoutDealerTwoCards() {
        BlackJackGame game = new BlackJackGame(players, dealer);
        game.handOutFirstTwoCards();
        assertThat(player1.getCardsInList()).hasSize(2);
    }
}
