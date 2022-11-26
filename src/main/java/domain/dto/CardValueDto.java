package domain.dto;

import java.util.List;
import java.util.Map;

public class CardValueDto {
    private final Map<String, String> playerCards;
    private final String dealerCards;

    public CardValueDto(Map<String, String> playerCards, String dealerCards) {
        this.playerCards = playerCards;
        this.dealerCards = dealerCards;
    }

    public Map<String, String> getPlayerCards() {
         return playerCards;
    }

    public String getDealerCards() {
        return dealerCards;
    }
}