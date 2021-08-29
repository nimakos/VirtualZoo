package gr.nikolis.sql.enums;

import java.util.Arrays;
import java.util.stream.Stream;

public enum SpecieEnum {
    dog(1, "dog"),
    cat(2, "cat"),
    fish(3, "fish");

    private final long code;
    private final String name;

    SpecieEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
    public long getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static SpecieEnum getTrick(long code) {
        Stream<SpecieEnum> specieStream = Arrays.stream(values()).filter(specieEnum -> specieEnum.getCode() == code);
        return specieStream.findFirst().orElse(null);
    }

    public static SpecieEnum getTrick(String name) {
        Stream<SpecieEnum> specieStream = Arrays.stream(values()).filter(specieEnum -> specieEnum.getName().equals(name));
        return specieStream.findFirst().orElse(null);
    }
}
