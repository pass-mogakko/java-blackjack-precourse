package view;


import constants.View;
import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import dto.UsersDTO;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static final String FIRST_HAND_OUT_MESSAGE = View.DEALER + "와 %s에게 2장의 카드를 나누었습니다.";

    public void printHandOut(List<Player> players) {
        System.out.printf(FIRST_HAND_OUT_MESSAGE + View.LINE_BREAK, getPlayerNames(players));
    }

    public void printHandOutResult(UsersDTO usersDTO) {
        printDealerCardFirstTime(usersDTO.getDealer());
        Players players = usersDTO.getPlayers();
        printPlayerCards(players.getPlayers());

    }

    private void printDealerCardFirstTime(Dealer dealer) {
        System.out.println(View.DEALER + View.COLON + getCardView(dealer.getFirstCard()));
    }

    private void printPlayerCards(List<Player> players) {
        players.stream()
                .forEach(player -> printPlayerCard(player));
    }

    public void printPlayerCard(Player player) {
        System.out.println(player.getName() + View.CARD + View.COLON + getCardsView(player.getCards()));
    }

    private String getPlayerNames(List<Player> players) {
        List<String> playerNames = players.stream()
                .map(player -> player.getName())
                .collect(Collectors.toList());
        return String.join(", ",playerNames);
    }

    private String getCardsView(List<Card> cards) {
        List<String> cardsView = cards.stream()
                .map(card -> getCardView(card))
                .collect(Collectors.toList());
        return String.join(", ",cardsView);
    }

    private String getCardView(Card card) {
        Type type = card.getType();
        Symbol symbol = card.getSymbol();
        return type.getTypeKoreanName() + symbol.getScore();
    }

}
