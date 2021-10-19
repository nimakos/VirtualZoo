package gr.nikolis.parsers.coronaData.coronaModels.summary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CoronaDataSummary {
    @JsonProperty("ID")
    private String id;
    @JsonProperty("Message")
    private String message;
    @JsonProperty("Global")
    private Global global;
    @JsonProperty("Countries")
    private List<Country> countries;
}
