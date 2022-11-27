package domain.card;

import domain.BlackJackGame;
import domain.user.Dealer;
import domain.user.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackJackGameTest {
    private final List<Player> players = new ArrayList<>();
    private final Dealer dealer = new Dealer(0);

    private void addPlayers() {
        players.add(new Player("pobi", 300));
        players.add(new Player("suan", 500));
    }

    @DisplayName("카드가 덱에서 제거되는지 확인")
    @Test
    void removeCardFromDeck() {
        addPlayers();
        BlackJackGame game = new BlackJackGame(players, dealer);
        game.handOutFirstTwoCards();
        assertThat(game.getDeckSize())
                .isEqualTo(46);
    }

    @DisplayName("플레이어의 카드가 두장씩 나누어졌는지 확인")
    @Test
    void checkHandoutPlayerTwoCards() {
        addPlayers();
        BlackJackGame game = new BlackJackGame(players, dealer);
        game.handOutFirstTwoCards();
        assertThat(players.get(0)
                .getCardsInList())
                .hasSize(2);
    }

    @DisplayName("딜러의 카드가 두장 주어졌는지 확인")
    @Test
    void checkHandoutDealerTwoCards() {
        BlackJackGame game = new BlackJackGame(players, dealer);
        game.handOutFirstTwoCards();
        assertThat(dealer.getCardsInList()).hasSize(2);
    }
}
