package domain;

import java.util.Arrays;

public enum MoreCard {
    YES("y"),
    NO("n");

    private final String command;

    MoreCard(String command) {
        this.command = command;
    }

    public static MoreCard getMoreCardByCommand(String command) {
        return Arrays.stream(MoreCard.values())
                .filter(value -> value.getCommand().equals(command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("입력한 명령어에 따른 값을 찾을 수 없습니다."));
    }

    private String getCommand() {
        return command;
    }

}
