package domain.dto;

import java.util.List;

public class CardValueDto {
    private final List<String> playerCards;
    private final String dealerCards;

    public CardValueDto(List<String> playerCards, String dealerCards) {
        this.playerCards = playerCards;
        this.dealerCards = dealerCards;
    }

    public List<String> getPlayerCards() {
         return playerCards;
    }

    public String getDealerCards() {
        return dealerCards;
    }

    public String getPlayerCard(int idx) {
        return playerCards.get(idx);
    }
}