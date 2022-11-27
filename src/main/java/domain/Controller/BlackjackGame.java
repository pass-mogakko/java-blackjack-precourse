package domain.Controller;

import static domain.Constants.InputValue.SELECTION_NO;

import domain.View.InputView;
import domain.View.OutputView;
import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class BlackjackGame {
    private static InputView inputView = new InputView();
    private static OutputView outputView = new OutputView();


    Dealer dealer = new Dealer();
    List<Player> players = createPlayers();
    Deck deck = new Deck();

    private static Player createPlayer(String playerName) {
        double bettingMoney = inputView.readBettingMoney(playerName);

        return new Player(playerName, bettingMoney);
    }

    private static List<Player> createPlayers() {
        List<String> playersName = inputView.readPlayers();
        List<Player> players = new ArrayList<>(playersName.size());

        for (String playerName : playersName) {
            Player player = createPlayer(playerName);
            players.add(player);
        }

        return Collections.unmodifiableList(players);
    }

    private static void setDealerState(Deck deck, Dealer dealer) {
        dealer.addCard(deck.drawCard());
        outputView.printDealerCards(dealer.getCards());
        dealer.addCard(deck.drawCard());
    }

    private static void setPlayerState(Deck deck, Player player) {
        player.addCard(deck.drawCard());
        player.addCard(deck.drawCard());
        outputView.printPlayerCards(player.getCards(), player.getName());

    }

    private static void setFirstState(Deck deck, Dealer dealer, List<Player> players) {
        setDealerState(deck, dealer);

        for (Player player : players) {
            setPlayerState(deck, player);
        }
    }

    private static void hitDealer(Deck deck, Dealer dealer) {
        do {
            System.out.println("16 이하라 한장 더");
            dealer.addCard(deck.drawCard());
            outputView.printDealerCards(dealer.getCards());
        } while (!dealer.isBust() && dealer.isShouldHit());
    }

    private static void hitPlayer(Deck deck, Player player) {
        do {
            String selection = inputView.readSelection(player.getName());

            if (selection.equals(SELECTION_NO)) {
                return;
            }
            player.addCard(deck.drawCard());
            outputView.printPlayerCards(player.getCards(), player.getName());
        } while (!player.isBust());

        System.out.println("Bust!\n");
    }

    private static void hitPlayers(Deck deck, List<Player> players) {
        for (Player player : players) {
            hitPlayer(deck, player);
        }
    }

    public void run() {
        setFirstState(deck, dealer, players);
        hitPlayers(deck, players);
        hitDealer(deck, dealer);

        outputView.printResultCards(dealer, players);
        outputView.printProfits(dealer, players);
    }
}
