package dto;

import java.util.List;

public class CardsToStringDto {

    private final List<PlayerCardsToStringDto> playerCardsToStringDtos;
    private final List<String> dealerHasCards;

    public CardsToStringDto(List<PlayerCardsToStringDto> playerCardsToStringDtos, List<String> dealerHasCards) {
        this.playerCardsToStringDtos = playerCardsToStringDtos;
        this.dealerHasCards = dealerHasCards;
    }

    public List<PlayerCardsToStringDto> getPlayerCardsToStringDtos() {
        return playerCardsToStringDtos;
    }

    public List<String> getDealerHasCards() {
        return dealerHasCards;
    }
}
