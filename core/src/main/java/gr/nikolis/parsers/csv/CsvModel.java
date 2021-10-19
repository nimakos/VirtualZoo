package gr.nikolis.parsers.csv;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class CsvModel {
    private long id;
    private String username;
    private String password;
    private String accessToken;
}
