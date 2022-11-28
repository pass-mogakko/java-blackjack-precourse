package view;

import dto.NewPlayerDTO;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import util.Converter;

public class InputView {

    private final static String INPUT_PLAYERS = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private final static String INPUT_BETTING_AMOUNT = "%s의 배팅 금액은?";

    private final static String ARRAY_DIVIDER = ",";

    public List<NewPlayerDTO> inputPlayers() {
        System.out.println(INPUT_PLAYERS);
        List<String> players = Arrays.asList(readLine().split(ARRAY_DIVIDER));
        // validate
        return Converter.convertNewPlayersDTO(players);
    }

    public int inputBettingAmount(NewPlayerDTO newPlayerDTO) {
        System.out.printf(INPUT_BETTING_AMOUNT,newPlayerDTO.getName());
        String bettingMoney = readLine();
        // validate
        return Converter.convertBettingMoney(bettingMoney);
    }

    public void inputMoreMoreDivide() {

    }

    private String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


}
