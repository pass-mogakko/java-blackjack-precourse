package model;

import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;
import model.validator.GameRuleValidator;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {

    private final List<Player> participants;
    private final Dealer dealer;
    private final Deck deck;

    public BlackJackGame() {
        this.participants = new ArrayList<>();
        this.dealer = new Dealer();
        this.deck = new Deck();
    }

    public void enrollPlayer(String name, double bettingMoney) {
        GameRuleValidator.validateEnrolledCount(participants.size());
        Player player = new Player(name, bettingMoney);
        GameRuleValidator.validateDuplicatedPlayer(participants.contains(player));
        participants.add(player);
    }

    public void addCardToPlayer(int playerIndex) {
        validatePlayerIndex(playerIndex);
        Player player = participants.get(playerIndex);
        player.addCard(deck.takeOneCard());
    }

    public void addCardToDealer() {
        dealer.addCard(deck.takeOneCard());
    }

    private void validatePlayerIndex(int playerIndex) {
        if (playerIndex > participants.size() - 1) {
            throw new IndexOutOfBoundsException("플레이어 정보가 존재하지 않습니다.");
        }
    }
}
