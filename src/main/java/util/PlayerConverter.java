package util;

import domain.user.Player;
import domain.user.Players;
import dto.NewPlayerDTO;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerConverter {
    public static Player convertPlayer(NewPlayerDTO newPlayerDTO) {
        return new Player(newPlayerDTO.getName(), newPlayerDTO.getBettingMoney());
    }

    public static Players convertPlayers(List<NewPlayerDTO> newPlayerDtos) {
        List<Player> players = newPlayerDtos.stream()
                .map(newPlayerDTO -> convertPlayer(newPlayerDTO))
                .collect(Collectors.toList());
        return new Players(players);
    }
}
