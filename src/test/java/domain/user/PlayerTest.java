package domain.user;

import static org.junit.jupiter.api.Assertions.*;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.Test;



class PlayerTest {
    Player player = new Player("player", 1000);
    Dealer dealer = new Dealer();

    @Test
    void 플레이어_버스트() {
        player.addCard(new Card(Symbol.JACK, Type.SPADE));
        player.addCard(new Card(Symbol.KING, Type.SPADE));
        player.addCard(new Card(Symbol.QUEEN, Type.SPADE));

        assertEquals(-1000, player.calculateProfit(dealer));
    }

    @Test
    void 플레이어_블랙잭() {
        player.addCard(new Card(Symbol.JACK, Type.SPADE));
        player.addCard(new Card(Symbol.ACE, Type.SPADE));

        assertEquals(1500, player.calculateProfit(dealer));
    }

    @Test
    void 플레이어_딜러_블랙잭() {
        player.addCard(new Card(Symbol.JACK, Type.SPADE));
        player.addCard(new Card(Symbol.ACE, Type.SPADE));

        dealer.addCard(new Card(Symbol.JACK, Type.CLUB));
        dealer.addCard(new Card(Symbol.ACE, Type.CLUB));

        assertEquals(0, player.calculateProfit(dealer));
    }

    @Test
    void 딜러_승리() {
        player.addCard(new Card(Symbol.JACK, Type.SPADE));
        player.addCard(new Card(Symbol.TWO, Type.SPADE));

        dealer.addCard(new Card(Symbol.JACK, Type.CLUB));
        dealer.addCard(new Card(Symbol.QUEEN, Type.CLUB));

        assertEquals(-1000, player.calculateProfit(dealer));
    }

    @Test
    void 플레이어_승리() {
        dealer.addCard(new Card(Symbol.JACK, Type.SPADE));
        dealer.addCard(new Card(Symbol.TWO, Type.SPADE));

        player.addCard(new Card(Symbol.JACK, Type.CLUB));
        player.addCard(new Card(Symbol.QUEEN, Type.CLUB));

        assertEquals(1000, player.calculateProfit(dealer));
    }
}