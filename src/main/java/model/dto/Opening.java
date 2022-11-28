package model.dto;

import java.util.List;

public class Opening {

    private final OpenedCards dealerCards;
    private final List<OpenedCards> playerCards;

    public Opening(OpenedCards dealerCards, List<OpenedCards> playerCards) {
        this.dealerCards = dealerCards;
        this.playerCards = playerCards;
    }

    public OpenedCards getDealerCards() {
        return dealerCards;
    }

    public List<OpenedCards> getPlayerCards() {
        return playerCards;
    }
}
