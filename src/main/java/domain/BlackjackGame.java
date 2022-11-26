package domain;

import domain.card.Card;
import domain.dto.CardValueDto;
import domain.user.Dealer;
import domain.user.Player;
import util.Converter;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {
    private final Converter converter = new Converter();

    public void start(List<Player> players, Dealer dealer, List<Card> cardDeck) {
        dealer.shuffleCards(cardDeck);
        dealer.giveCardsToPlayers(players, cardDeck);
        dealer.giveCardsToDealer(dealer, cardDeck);
    }

    public CardValueDto getCardValues(List<Player> players, Dealer dealer) {
        List<String> allPlayerCardValues = new ArrayList<>();
        for (Player player : players) {
            allPlayerCardValues.add(getPlayerCardValues(player));
        }
        String dealerCardValues = converter.convertListToStirng(getDealerCardValues(dealer));
        return new CardValueDto(allPlayerCardValues, dealerCardValues);
    }

    public List<String> getPlayerNameList(List<Player> players) {
        List<String> playerNames = new ArrayList<>();
        for (Player player : players) {
            playerNames.add(player.getName());
        }
        return playerNames;
    }

    private String getPlayerCardValues(Player player) {
        List<Card> cards = player.getCards();
        List<String> cardValues = new ArrayList<>();
        for (Card card : cards) {
            cardValues.add(card.getCardValue());
        }
        return converter.convertListToStirng(cardValues);
    }

    private List<String> getDealerCardValues(Dealer dealer) {
        List<Card> cards = dealer.getCards();
        List<String> cardValues = new ArrayList<>();
        for (Card card : cards) {
            cardValues.add(card.getCardValue());
        }
        return cardValues;
    }
}
