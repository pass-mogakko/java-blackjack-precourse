import java.io.IOException;
import java.util.List;

import static view.InputView.readPlayerName;

public class BlackJackGame {
    public static void main(String[] args) {
        System.out.println(getPlayerName());
    }

    private static List<String> getPlayerName(){
        try{
            return readPlayerName();
        }catch(IllegalArgumentException | IOException e){
            System.out.println(e.getMessage());
            return getPlayerName();
        }
    }
}
