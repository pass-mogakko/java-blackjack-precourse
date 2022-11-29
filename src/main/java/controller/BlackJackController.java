package controller;

import domain.status.MoreCard;
import domain.result.Result;
import domain.card.CardFactory;
import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import dto.NewPlayerDTO;
import dto.UsersDTO;
import java.util.List;
import service.BlackJackGame;
import util.converter.PlayerConverter;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final BlackJackGame blackJackGame;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackJackGame = initBlackJackGame();
    }

    public void run() {
        divideCardsFirstTime();
        blackJackGame.checkBlackJack();
        divideMoreCardToPlayers(blackJackGame.getNoResultPlayers());
        divideMoreCardToDealer();
        UsersDTO dto = blackJackGame.getUsers();
        printResult(dto);
        printProfit(dto, blackJackGame.getResult());
    }

    private void divideCardsFirstTime() {
        blackJackGame.divideFirstTime();
        UsersDTO dto = blackJackGame.getUsers();
        outputView.printHandOut(dto.getPlayers().getPlayers());
        outputView.printHandOutResult(dto);
    }

    private void divideMoreCardToDealer() {
        if (blackJackGame.isMoreCardToDealer()) {
            outputView.printMoreCardToDealer();
            blackJackGame.divideMoreCardToDealer();
        }
    }

    private void divideMoreCardToPlayers(List<Player> players) {
        players.stream()
                .forEach(player -> divideMoreCardToPlayer(player));
    }

    private void divideMoreCardToPlayer(Player player) {
        MoreCard moreCard;
        do {
            moreCard = inputView.inputMoreMoreDivide(player);
            divideMoreCardByCoomand(player, moreCard);
        } while (moreCard.equals(MoreCard.YES));
    }

    private void divideMoreCardByCoomand(Player player, MoreCard moreCard) {
        if (moreCard.equals(MoreCard.YES)) {
            blackJackGame.divideMoreCardOfPlayer(player.getName());
            outputView.printPlayerCards(player);
        }
        return;
    }

    private BlackJackGame initBlackJackGame() {
        Dealer dealer = new Dealer();
        Players players = createParticipants();
        Deck deck = new Deck(CardFactory.create());
        return new BlackJackGame(dealer, players, deck);
    }

    private Players createParticipants() {
        List<NewPlayerDTO> players = inputView.inputPlayers();
        betting(players);
        return PlayerConverter.convertPlayers(players);
    }

    private void betting(List<NewPlayerDTO> players) {
        players.stream()
                .forEach(player -> player.setBettingMoney(inputView.inputBettingAmount(player)));
    }

    private void printResult(UsersDTO dto) {
        outputView.printFinalResult(dto);
    }

    private void printProfit(UsersDTO dto, Result result) {
        blackJackGame.addResult(dto.getPlayers().getPlayers());
        outputView.printProfit(dto.getPlayers().getPlayers(), result.getResult());
    }

}
