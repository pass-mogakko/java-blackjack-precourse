package view.resource;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum TypeDisplay {

    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLUB("클로버");

    private final String display;
    private static final Map<String, String> displayByName = Arrays.stream(values())
                    .collect(Collectors.toMap(Enum::name, TypeDisplay::getDisplay));

    TypeDisplay(String display) {
        this.display = display;
    }

    private String getDisplay() {
        return display;
    }

    public static String findByName(String name) {
        return displayByName.get(name);
    }
}
