package domain.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck implements CardDistributor {

    private final Queue<Card> deck;

    public Deck() {
        List<Card> toBeShuffled = new LinkedList<>(CardFactory.create());
        Collections.shuffle(toBeShuffled);
        this.deck = new LinkedList<>(toBeShuffled);
    }

    @Override
    public Card takeOneCard() {
        validateDeckStatus();
        return deck.poll();
    }

    private void validateDeckStatus() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("덱의 모든 카드가 소진되었습니다.");
        }
    }
}
