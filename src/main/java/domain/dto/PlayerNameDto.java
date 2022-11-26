package domain.dto;

import util.Converter;

import java.util.List;

public class PlayerNameDto {
    private final List<String> names;
    private final String nameValues;

    private final Converter converter = new Converter();

    public PlayerNameDto(List<String> names) {
        this.names = names;
        this.nameValues = converter.convertListToStirng(names);
    }

    public List<String> getNames() {
        return names;
    }

    public String getNameValues() {
        return nameValues;
    }
}
