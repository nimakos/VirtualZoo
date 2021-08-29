package gr.nikolis.sql.enums;

import java.util.Arrays;
import java.util.stream.Stream;

public enum TrickEnum {

    walksOnLaptop(1, "walksOnLaptop"),
    jumps(2, "jumps"),
    rollsOver(3, "rollsOver"),
    barks(4, "barks"),
    lie(5, "lie"),
    none(6, "");

    private final long code;
    private final String name;

    TrickEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
    public long getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static TrickEnum getTrick(long code) {
        Stream<TrickEnum> tricksStream = Arrays.stream(values()).filter(trickEnum -> trickEnum.getCode() == code);
        return tricksStream.findFirst().orElse(none);
    }

    public static TrickEnum getTrick(String name) {
        Stream<TrickEnum> rolesStream = Arrays.stream(values()).filter(trickEnum -> trickEnum.getName().equals(name));
        return rolesStream.findFirst().orElse(none);
    }
}
