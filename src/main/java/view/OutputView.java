package view;

import constants.View;
import domain.GameResult;
import domain.ProfitCalculator;
import domain.Result;
import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.user.Dealer;
import domain.user.Participants;
import domain.user.Player;
import domain.user.Players;
import dto.UsersDTO;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static final String FIRST_HAND_OUT_MESSAGE = View.DEALER + "와 %s에게 2장의 카드를 나누었습니다.";
    public static final String MORE_CARD_TO_DEALER = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    public static final String FINAL_RESULT = " - 결과: %d";
    public static final String FINAL_PROFIT = "## 최종수익";

    public void printHandOut(List<Player> players) {
        System.out.printf(FIRST_HAND_OUT_MESSAGE + View.LINE_BREAK, getPlayerNames(players));
    }

    public void printHandOutResult(UsersDTO usersDTO) {
        printDealerCardFirstTime(usersDTO.getDealer());
        Players players = usersDTO.getPlayers();
        printPlayersCards(players.getPlayers());
    }

    // 결과 출력
    public void printFinalResult(UsersDTO usersDTO) {
        printDealerResult(usersDTO.getDealer());
        Players players = usersDTO.getPlayers();
        players.getPlayers()
                .stream()
                .forEach(player -> printPlayerResult(player));
    }

    // 딜러 결과 출력
    private void printDealerResult(Dealer dealer) {
        printDealerCards(dealer);
        printResult(dealer);
        lineBreak();
    }

    // 플레이어 결과 출력
    private void printPlayerResult(Player player) {
        System.out.print(getPlayerCards(player));
        printResult(player);
        lineBreak();
    }

    private void printResult(Participants participants) {
        System.out.printf(FINAL_RESULT, participants.getTotal());
    }

    public void printMoreCardToDealer() {
        System.out.println(MORE_CARD_TO_DEALER);
    }

    // 수익 출력
    public void printProfit(List<Player> players, Map<Player, GameResult> gameResult) {
        System.out.println(FINAL_PROFIT);
        int dealerProfit = -1 * players.stream()
                .mapToInt(player -> ProfitCalculator.calculateProfit((int) player.getBettingMoney(), gameResult.get(player)))
                .sum();

        printDealerProfit(dealerProfit);
        players.stream()
                .forEach(player -> printPlayerProfit(player, gameResult));
    }

    private void printDealerProfit(int dealerProfit) {
        System.out.println("딜러: " + dealerProfit);
    }

    private void printPlayerProfit(Player player, Map<Player, GameResult> result) {
        System.out.println(player.getName() + View.COLON + ProfitCalculator.calculateProfit((int) player.getBettingMoney(), result.get(player)));
    }

    private void printDealerCardFirstTime(Dealer dealer) {
        System.out.println(View.DEALER + View.COLON + getCardView(dealer.getFirstCard()));
    }

    // 딜러의 카드 출력
    private void printDealerCards(Dealer dealer) {
        System.out.print(View.DEALER + View.COLON + getCardsView(dealer.getCards()));
    }
    
    // 플레이어들의 카드 출력
    private void printPlayersCards(List<Player> players) {
        players.stream()
                .forEach(player -> printPlayerCards(player));
    }
    
    // 플레이어 카드 출력
    public void printPlayerCards(Player player) {
        System.out.println(getPlayerCards(player));
    }

    private String getPlayerCards(Player player) {
        return player.getName() + View.CARD + View.COLON + getCardsView(player.getCards());
    }

    private String getPlayerNames(List<Player> players) {
        List<String> playerNames = players.stream()
                .map(player -> player.getName())
                .collect(Collectors.toList());
        return String.join(", ", playerNames);
    }

    // '스페이드5, 클럽2' 와 같은 출력문 얻기
    private String getCardsView(List<Card> cards) {
        List<String> cardsView = cards.stream()
                .map(card -> getCardView(card))
                .collect(Collectors.toList());
        return String.join(", ", cardsView);
    }

    // '스페이드5' 와 같은 출력문 얻기
    private String getCardView(Card card) {
        Type type = card.getType();
        Symbol symbol = card.getSymbol();
        return type.getTypeKoreanName() + symbol.getScore();
    }

    // 줄바꿈 기능
    private void lineBreak() {
        System.out.println();
    }

}
