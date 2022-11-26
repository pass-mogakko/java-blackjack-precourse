package model.validator;

import static domain.rule.BettingRule.INITIAL_MAX_VALUE;
import static domain.rule.BettingRule.INITIAL_MIN_VALUE;
import static domain.rule.ParticipantsRule.PARTICIPANTS_MAX_COUNT;

public class GameRuleValidator {

    private GameRuleValidator() { }

    public static void validateEnrolledCount(int enrolledCount) {
        if (enrolledCount >= PARTICIPANTS_MAX_COUNT.getValue()) {
            throw new IllegalArgumentException("참여 가능 인원은 최대 " + PARTICIPANTS_MAX_COUNT.getValue() + "명입니다.");
        }
    }

    public static void validateDuplicatedPlayer(boolean hasSamePlayer) {
        if (hasSamePlayer) {
            throw new IllegalArgumentException("이미 동일한 이름의 사용자가 참여 목록에 등록되어있습니다.");
        }
    }

    public static void validateInitialBettingMoney(double bettingMoney) {
        if (bettingMoney < INITIAL_MIN_VALUE.getValue()) {
            throw new IllegalArgumentException("베팅 가능 금액은 최소 " + INITIAL_MIN_VALUE.getValue() + "원입니다.");
        }
        if (bettingMoney > INITIAL_MAX_VALUE.getValue()) {
            throw new IllegalArgumentException("베팅 가능 금액은 최대 " + INITIAL_MAX_VALUE.getValue() + "원입니다.");
        }
    }
}
