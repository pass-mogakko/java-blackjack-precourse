package model;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import model.validator.GameRuleValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private final List<Player> participants;
    private final Dealer dealer;
//    private final CardDistributor cardDistributor;

    public BlackJackGame(List<Card> trumpCard) {
        this.participants = new ArrayList<>();
        this.dealer = new Dealer();
//        this.cardDistributor = new CardDistributor(trumpCard);
    }

    public void enrollParticipants(Map<String, Integer> namesWithBettingMoney) {
        GameRuleValidator.validateParticipantsCount(namesWithBettingMoney.size());
        namesWithBettingMoney.keySet()
                .forEach(name -> participants.add(new Player(name, namesWithBettingMoney.get(name))));
    }
}
