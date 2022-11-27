package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 트럼프 카드 전체 생성을 담당하는 객체
 */
public class CardFactory {
    private final HashMap<Integer, Card> deck;

    public CardFactory(HashMap<Integer, Card> deck) {
        this.deck = deck;
    }

    public HashMap<Integer, Card> createDeck() {
        int index = 0;
        for (Card card : create()) {
            deck.put(index, card);
            index++;
        }
        return deck;
    }

    private static List<Card> create() {
        List<Card> cards = new ArrayList<>();
        Symbol[] symbols = Symbol.values();
        for (Symbol symbol : symbols) {
            createByType(cards, symbol);
        }
        return Collections.unmodifiableList(cards);
    }

    private static void createByType(List<Card> cards, Symbol symbol) {
        Type[] types = Type.values();
        for (Type type : types) {
            cards.add(new Card(symbol, type));
        }
    }

    public boolean findNumber(int index) {
        return deck.containsKey(index);
    }

}
