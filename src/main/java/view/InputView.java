package view;

import constants.View;
import domain.MoreCard;
import domain.user.Player;
import dto.NewPlayerDTO;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import util.converter.NewPlayerConverter;

public class InputView {

    private final static String INPUT_PLAYERS = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private final static String INPUT_BETTING_AMOUNT = "%s의 배팅 금액은?";
    private final static String INPUT_MORE_CARDS = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";

    private final static String ARRAY_DIVIDER = ",";

    public List<NewPlayerDTO> inputPlayers() {
        System.out.println(INPUT_PLAYERS);
        List<String> players = Arrays.asList(readLine().split(ARRAY_DIVIDER));
        // validate
        return NewPlayerConverter.convertNewPlayersDTO(players);
    }

    public int inputBettingAmount(NewPlayerDTO newPlayerDTO) {
        System.out.printf(INPUT_BETTING_AMOUNT,newPlayerDTO.getName());
        String bettingMoney = readLine();
        // validate
        return NewPlayerConverter.convertBettingMoney(bettingMoney);
    }

    public MoreCard inputMoreMoreDivide(Player player) {
        System.out.printf(INPUT_MORE_CARDS + View.LINE_BREAK,player.getName());
        String command = readLine();
        // validate
        return MoreCard.getMoreCardByCommand(command);
    }

    private String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


}
