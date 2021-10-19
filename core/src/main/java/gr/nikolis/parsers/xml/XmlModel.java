package gr.nikolis.parsers.xml;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public final class XmlModel {
    private long id;
    private String username;
    private String password;
    private String accessToken;
    private List<MyData> myData;
}


