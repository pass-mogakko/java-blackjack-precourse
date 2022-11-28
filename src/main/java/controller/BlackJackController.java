package controller;

import domain.card.CardFactory;
import domain.card.Deck;
import domain.user.Dealer;
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
    }

    private void handOutFirstTime() {
        blackJackGame.handOutFirstTime();
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
