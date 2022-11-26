package domain;

import domain.card.Card;
import domain.dto.BlackjackResultDto;
import domain.dto.GameResultDto;
import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {

    public void start(List<Player> players, Dealer dealer, List<Card> cardDeck) {
        dealer.giveCardsToPlayers(players, cardDeck);
        dealer.giveCardsToDealer(cardDeck);
    }

    public Object play(List<Player> players, Dealer dealer, List<Card> cardDeck) {
//        if (findAffordablePlayers(players).size() > 0)
//            return findAffordablePlayers(players);
        if (dealer.getAdditionalCard(cardDeck))
            return true;
        return new GameResultDto(true, true, List.of(), true);  // 테스트용
    }

    public boolean isBlackjack(List<Player> players, Dealer dealer) {
        return dealer.isBlackjack() || playerGotBlackjack(players);
    }

    public BlackjackResultDto buildBlackjackResult(List<Player> players, Dealer dealer) {
        List<Player> blackjackPlayers = findBlackjackPlayers(players);
        boolean dealerBlackjack = dealer.isBlackjack();
        boolean playerBlackjack = blackjackPlayers.size() > 0;
        return new BlackjackResultDto(dealerBlackjack, playerBlackjack, blackjackPlayers);
    }

    private boolean playerGotBlackjack(List<Player> players) {
        return findBlackjackPlayers(players).size() > 0;
    }

    private List<Player> findBlackjackPlayers(List<Player> players) {
        List<Player> blackjackPlayers = new ArrayList<>();
        for (Player player : players) {
            if (player.isBlackjack()) {
                blackjackPlayers.add(player);
            }
        }
        return blackjackPlayers;
    }

    public List<Player> findAffordablePlayers(List<Player> players) {
        List<Player> result = new ArrayList<>();
        for (Player player : players) {
            if (player.isAffordable()) {
                result.add(player);
            }
        }
        return result;
    }
}