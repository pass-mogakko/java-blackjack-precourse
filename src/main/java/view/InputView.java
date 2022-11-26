package view;

import java.util.Scanner;
import view.constant.Message;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public String requestPlayerName() {
        System.out.println(Message.REQUEST_PLAYER_NAME);
        return scanner.nextLine();
    }

    public String requestPlayerBettingMoney(String playerName) {
        System.out.println();
        System.out.printf(Message.REQUEST_PLAYER_BETTING_MONEY, playerName);
        System.out.println();
        return scanner.nextLine();
    }

    public String requestDrawCard(String playerName) {
        System.out.printf(Message.REQUEST_DRAW_CARD, playerName);
        System.out.println();
        return scanner.nextLine();
    }
}
