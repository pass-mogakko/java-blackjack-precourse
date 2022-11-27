import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static view.InputView.readBettingMoney;
import static view.InputView.readPlayerName;

public class BlackJackGame {
    public static void main(String[] args) {
        List<String> playerName = getPlayerName();
        List<Integer> bettingMoney = getBettingMoneyInList(playerName);
        System.out.println(bettingMoney);

    }

    private static List<String> getPlayerName(){
        try{
            return readPlayerName();
        }catch(IllegalArgumentException | IOException e){
            System.out.println(e.getMessage());
            return getPlayerName();
        }
    }

    private static List<Integer> getBettingMoneyInList(List<String> playerName){
        List<Integer> bettingMoney = new ArrayList<>();
        for(String name: playerName){
            bettingMoney.add(getBettingMoney(name));
        }
        return bettingMoney;
    }

    private static int getBettingMoney(String name){
        try{
            return readBettingMoney(name);
        }catch(IllegalArgumentException | IOException e){
            System.out.println(e.getMessage());
            return getBettingMoney(name);
        }
    }
}
