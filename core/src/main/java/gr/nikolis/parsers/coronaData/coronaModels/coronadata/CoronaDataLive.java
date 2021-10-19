package gr.nikolis.parsers.coronaData.coronaModels.coronadata;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class CoronaDataLive {
    @JsonProperty("ID")
    private String id;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("CountryCode")
    private String countryCode;
    @JsonProperty("Province")
    private String province;
    @JsonProperty("City")
    private String city;
    @JsonProperty("CityCode")
    private String cityCode;
    @JsonProperty("Lat")
    private String lat;
    @JsonProperty("Lon")
    private String lon;
    @JsonProperty("Confirmed")
    private Long confirmed;
    @JsonProperty("Deaths")
    private Long deaths;
    @JsonProperty("Recovered")
    private Long recovered;
    @JsonProperty("Active")
    private Long active;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", locale = "el_GR", timezone = "Europe/Athens")
    @JsonProperty("Date")
    private Date date;
    @JsonIgnore
    private long newDeaths;
    @JsonIgnore
    private long newRecovered;
    @JsonIgnore
    private long newConfirmed;
    @JsonIgnore
    private long newActive;

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(getDate());
        return "Ημ/νια : " + strDate + ", Νέα ενεργά κρούσματα : " + getNewActive() + ", Νέα Επιβεβαιωμένα : " + getNewConfirmed() + ", Νέοι θάνατοι : " + getNewDeaths();
    }
}
