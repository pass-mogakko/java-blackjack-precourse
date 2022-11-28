package model.dto;

import java.util.ArrayList;
import java.util.List;

public class Earnings {

    private final List<Earning> playerEarnings = new ArrayList<>();
    private final Earning dealerEarning = new Earning("딜러");

    public Earnings(List<String> playerNames) {
        initializePlayerEarnings(playerNames);
    }

    private void initializePlayerEarnings(List<String> playerNames) {
        validatePlayerNames(playerNames);
        playerNames.forEach(name -> playerEarnings.add(new Earning(name)));
    }

    private void validatePlayerNames(List<String> playerNames) {
        if (playerNames == null) {
            throw new NullPointerException("게임 참가자 이름 정보가 null입니다.");
        }
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException("게임 참가자 이름 정보가 존재하지 않습니다.");
        }
    }

    public Earning getDealerEarning() {
        return dealerEarning;
    }

    public List<Earning> getPlayerEarnings() {
        return playerEarnings;
    }

    public void moveEarningFromDealerToPlayer(String playerName, double amount) {
        dealerEarning.updateEarning(-amount);
        updatePlayerEarningsByName(playerName, amount);
    }

    private void updatePlayerEarningsByName(String playerName, double amount) {
        Earning earning = findPlayerEarningByName(playerName);
        earning.updateEarning(amount);
    }

    private Earning findPlayerEarningByName(String playerName) {
        return playerEarnings.stream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> {throw new IllegalArgumentException("해당 이름을 가진 사용자가 존재하지 않습니다.");});
    }
}
