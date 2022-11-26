package util;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    public List<String> convertToNames(String str) {
        List<String> names = new ArrayList<>(List.of(str.split(",")));
        return names;
    }

}
