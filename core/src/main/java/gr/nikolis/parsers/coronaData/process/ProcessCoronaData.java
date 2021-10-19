package gr.nikolis.parsers.coronaData.process;

import gr.nikolis.parsers.coronaData.coronaModels.coronadata.CoronaDataLive;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProcessCoronaData {

    public List<CoronaDataLive> processData(List<CoronaDataLive> coronaDatumLives) {
        int count = 0;
        for (CoronaDataLive data : coronaDatumLives) {
            if (count != 0) {
                data.setNewDeaths(data.getDeaths() - coronaDatumLives.get(count - 1).getDeaths());
                data.setNewRecovered(data.getRecovered() - coronaDatumLives.get(count - 1).getRecovered());
                data.setNewConfirmed(data.getConfirmed() - coronaDatumLives.get(count - 1).getConfirmed());
                data.setNewActive(data.getActive() - coronaDatumLives.get(count - 1).getActive());
            }
            count++;
        }
        return coronaDatumLives;
    }
}
