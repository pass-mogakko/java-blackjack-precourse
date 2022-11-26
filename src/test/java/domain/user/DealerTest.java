package domain.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



class DealerTest {
    Dealer dealer = new Dealer();

    @DisplayName("정상적으로 딜러에게 카드가 주어지는지 확인")
    @Test
    void giveCardToSelf() {
        dealer.giveCardToSelf();

        System.out.println(dealer.getCards());
    }

    @DisplayName("정상적으로 플레이어에게 카드가 주어지는지 확인")
    @Test
    void giveCardToPlayer() {
        Player player = new Player("name", 1000);

        dealer.giveCardToPlayer(player);

        System.out.println(player.getCards());
    }
}