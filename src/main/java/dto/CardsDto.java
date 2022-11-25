package dto;

import java.util.List;

public class CardsDto {

    private final List<String> dealerHasCard;
    private final List<List<String>> playersHasCard;

    public CardsDto(List<String> dealerHasCard, List<List<String>> playersHasCard) {
        this.dealerHasCard = dealerHasCard;
        this.playersHasCard = playersHasCard;
    }

    public List<String> getDealerHasCard() {
        return dealerHasCard;
    }

    public List<List<String>> getPlayersHasCard() {
        return playersHasCard;
    }
}
