package gr.nikolis.parsers.xml;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Students {
    @JsonProperty("student")
    List<Student> students;
}
