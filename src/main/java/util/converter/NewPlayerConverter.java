package util.converter;

import dto.NewPlayerDTO;
import java.util.List;
import java.util.stream.Collectors;

public class NewPlayerConverter {
    public static NewPlayerDTO convertNewPlayerDTO(String name) {
        return new NewPlayerDTO(name);
    }

    public static List<NewPlayerDTO> convertNewPlayersDTO(List<String> names) {
        return names.stream()
                .map(NewPlayerDTO::new)
                .collect(Collectors.toList());
    }

    public static int convertBettingMoney(String bettingMoney) {
        return Integer.parseInt(bettingMoney);
    }

}
