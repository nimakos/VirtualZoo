package gr.nikolis.parsers.xml;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Student {
    private int id;
    private String givenName;
    private String surname;
    private String marks;
}
