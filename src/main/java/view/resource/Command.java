package view.resource;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum Command {

    HIT("y", true),
    STAY("n", false);

    private final String keyword;
    private final boolean toHit;
    private static final Map<String, Boolean> toHitByCommand = Arrays.stream(values())
                    .collect(Collectors.toMap(Command::getKeyword, Command::isToHit));

    Command(String keyword, boolean toHit) {
        this.keyword = keyword;
        this.toHit = toHit;
    }

    public static boolean isToHitByCommand(String keyword) {
        Boolean toHit = toHitByCommand.get(keyword);
        if (toHit == null) {
            throw new IllegalArgumentException("잘못된 키워드입니다.");
        }
        return toHit;
    }

    private String getKeyword() {
        return keyword;
    }

    private boolean isToHit() {
        return toHit;
    }
}
