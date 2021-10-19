package gr.nikolis.parsers.coronaData.coronaModels.summary;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Global {
    @JsonProperty("NewConfirmed")
    private long newConfirmed;
    @JsonProperty("TotalConfirmed")
    private long totalConfirmed;
    @JsonProperty("NewDeaths")
    private long newDeaths;
    @JsonProperty("TotalDeaths")
    private long totalDeaths;
    @JsonProperty("NewRecovered")
    private long newRecovered;
    @JsonProperty("TotalRecovered")
    private long totalRecovered;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:sss", locale = "el_GR", timezone = "Europe/Athens")
    @JsonProperty("Date")
    private Date date;
}
