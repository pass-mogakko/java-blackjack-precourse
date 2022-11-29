package domain;

public class ProfitCalculator {

    public static int calculateProfit(int bettingMoney, GameResult gameResult) {
        if (gameResult.equals(GameResult.WIN)) {
            return bettingMoney;
        }

        if (gameResult.equals(GameResult.LOSE)) {
            return -bettingMoney;
        }
            return 0;
    }

}
