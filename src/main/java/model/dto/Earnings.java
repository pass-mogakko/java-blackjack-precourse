package model.dto;

import java.util.ArrayList;
import java.util.List;

public class Earnings {

    private final List<Earning> playerEarnings = new ArrayList<>();
    private double dealerEarning;

    public Earnings(List<String> playerNames) {
        this.dealerEarning = 0;
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

    public double getDealerEarning() {
        return dealerEarning;
    }

    public Earning findPlayerEarningByName(String playerName) {
        return playerEarnings.stream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> {throw new IllegalArgumentException("해당 이름을 가진 사용자가 존재하지 않습니다.");});
    }

    public void moveEarningFromDealerToPlayer(String playerName, double amount) {
        dealerEarning -= amount;
        updatePlayerEarningsByName(playerName, amount);
    }

    private void updatePlayerEarningsByName(String playerName, double amount) {
        Earning earning = findPlayerEarningByName(playerName);
        earning.updateEarning(amount);
    }

    @Override
    public String toString() {
        return "dealerEarning=" + dealerEarning +" playerEarnings=" + playerEarnings;
    }
}
