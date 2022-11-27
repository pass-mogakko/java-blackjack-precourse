package model;

import static domain.rule.ScoreRule.POINT_DEALER_ADD_LIMIT;

import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;
import model.validator.GameRuleValidator;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {

    private final List<Player> participants; // TODO User로 타입 변경, dealer도 저장
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

    public boolean addCardToDealerIfValid() {
        int score = dealer.addAllScore();
        if (score < POINT_DEALER_ADD_LIMIT.getValue()) {
            dealer.addCard(deck.takeOneCard());
            return true;
        }
        return false;
    }

    private void validatePlayerIndex(int playerIndex) {
        if (playerIndex > participants.size() - 1) {
            throw new IndexOutOfBoundsException("플레이어 정보가 존재하지 않습니다.");
        }
    }

    public void distributeCardsForStart() {
        for (int index = 0; index < participants.size(); index++) {
            addCardToPlayer(index);
            addCardToPlayer(index);
        }
        dealer.addCard(deck.takeOneCard());
        dealer.addCard(deck.takeOneCard());
    }

    public OpenedCardsDto openAllUserCards(boolean showAllDealerCards) {
        if (!showAllDealerCards) {
            Dealer cardHiddenDealer = new Dealer();
            cardHiddenDealer.addCard(dealer.getCards().get(0));
            return new OpenedCardsDto(cardHiddenDealer, participants);
        }
        return new OpenedCardsDto(dealer, participants);
    }

    public OpenedCardsDto openPlayerCards(int playerIndex) {
        return new OpenedCardsDto(null, List.of(participants.get(playerIndex)));
    }
}
