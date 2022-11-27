package domain.user;

import static org.junit.jupiter.api.Assertions.*;

import domain.Application;
import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;



class DealerTest {
    Player first = new Player("first", 1000);
    Player second = new Player("second", 2000);
    Dealer dealer = new Dealer();
    List<Player> players = new ArrayList<>(Arrays.asList(first, second));

    @Test
    void 플레이어_블랙잭_수익계산() {
        first.addCard(new Card(Symbol.ACE, Type.CLUB));
        first.addCard(new Card(Symbol.KING, Type.CLUB));

        second.addCard(new Card(Symbol.ACE, Type.SPADE));
        second.addCard(new Card(Symbol.KING, Type.SPADE));

        assertEquals(-4500, dealer.calculateProfit(players));
    }

    @Test
    void 플레이어_딜러_블랙잭_수익계산() {
        first.addCard(new Card(Symbol.ACE, Type.CLUB));
        first.addCard(new Card(Symbol.KING, Type.CLUB));

        second.addCard(new Card(Symbol.ACE, Type.SPADE));
        second.addCard(new Card(Symbol.KING, Type.SPADE));

        dealer.addCard(new Card(Symbol.ACE, Type.HEART));
        dealer.addCard(new Card(Symbol.KING, Type.HEART));

        assertEquals(0, dealer.calculateProfit(players));
    }

    @Test
    void 딜러_블랙잭_수익계산() {
        first.addCard(new Card(Symbol.NINE, Type.HEART));
        first.addCard(new Card(Symbol.KING, Type.HEART));

        second.addCard(new Card(Symbol.NINE, Type.CLUB));
        second.addCard(new Card(Symbol.KING, Type.CLUB));

        dealer.addCard(new Card(Symbol.ACE, Type.SPADE));
        dealer.addCard(new Card(Symbol.KING, Type.SPADE));

        assertEquals(3000, dealer.calculateProfit(players));
    }

    @Test
    void 플레이어_버스트_수익계산() {
        first.addCard(new Card(Symbol.NINE, Type.CLUB));
        first.addCard(new Card(Symbol.KING, Type.CLUB));
        first.addCard(new Card(Symbol.JACK, Type.CLUB));

        assertEquals(1000, dealer.calculateProfit(players));
    }

    @Test
    void 딜러_버스트_수익계산() {
        first.addCard(new Card(Symbol.NINE, Type.HEART));
        first.addCard(new Card(Symbol.KING, Type.HEART));

        second.addCard(new Card(Symbol.NINE, Type.HEART));
        second.addCard(new Card(Symbol.KING, Type.HEART));

        dealer.addCard(new Card(Symbol.NINE, Type.CLUB));
        dealer.addCard(new Card(Symbol.KING, Type.CLUB));
        dealer.addCard(new Card(Symbol.JACK, Type.CLUB));

        assertEquals(-3000, dealer.calculateProfit(players));
    }

    @Test
    void 플레이어_딜러_버스트_수익계산() {   // 플레이어가 먼저 버스트 e되므로 딜러가 수익을 얻음
        first.addCard(new Card(Symbol.NINE, Type.HEART));
        first.addCard(new Card(Symbol.KING, Type.HEART));
        first.addCard(new Card(Symbol.JACK, Type.HEART));

        second.addCard(new Card(Symbol.NINE, Type.SPADE));
        second.addCard(new Card(Symbol.KING, Type.SPADE));
        second.addCard(new Card(Symbol.JACK, Type.SPADE));

        dealer.addCard(new Card(Symbol.NINE, Type.CLUB));
        dealer.addCard(new Card(Symbol.KING, Type.CLUB));
        dealer.addCard(new Card(Symbol.JACK, Type.CLUB));

        assertEquals(3000, dealer.calculateProfit(players));
    }

    @Test
    void 플레이어_딜러_동점() {
        first.addCard(new Card(Symbol.NINE, Type.HEART));

        second.addCard(new Card(Symbol.NINE, Type.SPADE));

        dealer.addCard(new Card(Symbol.NINE, Type.CLUB));

        assertEquals(0, dealer.calculateProfit(players));
    }
}