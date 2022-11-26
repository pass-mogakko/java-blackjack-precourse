package view.resource;

public enum Format {

    NAMES_DELIMITER(",");

    private final String value;

    Format(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
