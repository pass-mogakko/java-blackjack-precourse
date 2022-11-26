package service;

import domain.card.RandomCards;
import domain.user.Dealer;
import java.util.List;

public class DealerService {

    private static final DealerService dealerService = new DealerService();
    private static Dealer dealer = new Dealer();
    private static RandomCards randomCards = RandomCards.getInstance();

    private DealerService() {

    }

    public static DealerService getInstance() {
        return dealerService;
    }

    public List<String> initCards() {
        return dealer.initCards();
    }

    public boolean isPossibleDrawCard() {
        return dealer.isPossibleDrawCard();
    }

    public void drawCard() {
        dealer.addCard(randomCards.drawCard());
    }

    public List<String> findDealerHasCards() {
        return dealer.collectCardToString();
    }

    public int computeScore() {
        return dealer.computeScore();
    }
}
