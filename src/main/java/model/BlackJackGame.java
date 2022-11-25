package model;

import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;
import model.validator.GameRuleValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private final List<Player> participants;
    private final Dealer dealer;
    private final Deck deck;

    public BlackJackGame() {
        this.participants = new ArrayList<>();
        this.dealer = new Dealer();
        this.deck = new Deck();
    }

    public void enrollParticipants(Map<String, Integer> namesWithBettingMoney) {
        GameRuleValidator.validateParticipantsCount(namesWithBettingMoney.size());
        namesWithBettingMoney.keySet()
                .forEach(name -> participants.add(new Player(name, namesWithBettingMoney.get(name))));
    }
}
