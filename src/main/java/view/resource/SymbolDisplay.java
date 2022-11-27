package view.resource;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum SymbolDisplay {

    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");

    private final String display;
    private static final Map<String, String> displayBySymbolName = Arrays.stream(values())
                    .collect(Collectors.toMap(Enum::name, SymbolDisplay::getDisplay));

    SymbolDisplay(String display) {
        this.display = display;
    }

    private String getDisplay() {
        return display;
    }

    public static String findBySymbolName(String symbolName) {
        return displayBySymbolName.get(symbolName);
    }
}
