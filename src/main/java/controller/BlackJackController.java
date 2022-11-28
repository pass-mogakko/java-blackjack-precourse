package controller;

import domain.MoreCard;
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
        handOutFirstTime();
        moreCardByPlayers(blackJackGame.getUsers().getPlayers());
        divideMoreCardToDealer();

        UsersDTO dto = blackJackGame.getUsers();
        outputView.printAllResult(dto);
    }

    private void handOutFirstTime() {
        blackJackGame.handOutFirstTime();
        UsersDTO dto = blackJackGame.getUsers();
        outputView.printHandOut(dto.getPlayers().getPlayers());
        outputView.printHandOutResult(dto);
    }

    private void moreCardByPlayers(Players players) {
        players.getPlayers()
                .stream()
                .forEach(player -> moreCard(player));
    }

    private void divideMoreCardToDealer() {
        if (blackJackGame.isMoreCardToDealer()) {
            outputView.printMoreCardToDealer();
            blackJackGame.divideMoreCardToDealer();
        }
    }

    private void moreCard(Player player) {
        MoreCard moreCard;
        do {
            moreCard = inputView.inputMoreMoreDivide(player);
            handOutMoreCard(player, moreCard);
        } while (moreCard.equals(MoreCard.YES));
    }

    private void handOutMoreCard(Player player, MoreCard moreCard) {
        if (moreCard.equals(MoreCard.YES)) {
            blackJackGame.handOutMoreCardOfPlayer(player.getName());
            outputView.printPlayerCard(player);
        }
        return;
    }

    private BlackJackGame initBlackJackGame() {
        Dealer dealer = new Dealer();
        Players players = applyParticipation();
        Deck deck = new Deck(CardFactory.create());
        return new BlackJackGame(dealer, players, deck);
    }

    private Players applyParticipation() {
        List<NewPlayerDTO> players = inputView.inputPlayers();
        betting(players);
        return PlayerConverter.convertPlayers(players);
    }

    private void betting(List<NewPlayerDTO> players) {
        players.stream()
                .forEach(player -> player.setBettingMoney(inputView.inputBettingAmount(player)));
    }

}
