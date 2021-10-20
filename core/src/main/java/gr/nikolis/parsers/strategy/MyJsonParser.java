package gr.nikolis.parsers.strategy;


import gr.nikolis.parsers.coronaData.coronaModels.coronadata.CoronaDataLive;
import gr.nikolis.parsers.coronaData.coronaModels.summary.CoronaDataSummary;
import gr.nikolis.parsers.coronaData.process.ProcessCoronaData;
import gr.nikolis.parsers.enums.ParserName;
import gr.nikolis.parsers.factory.Parser;
import gr.nikolis.parsers.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyJsonParser extends Parser {

    private static final String URL1 = "https://api.covid19api.com/summary";
    private static final String URL2 = "https://api.covid19api.com/live/country/greece/status/confirmed/date/2020-01-01";
    private static final String[] header = {"X-Access-Token", "5cf9dfd5-3449-485e-b5ae-70a60e997864"};

    @Autowired
    private ProcessCoronaData process;
    @Autowired
    private Json json;

    //@Scheduled(cron = "* * * * * *") //Run every second
    @Override
    public void run() {
        List<CoronaDataLive> coronaDataLive = json.toListFromRest(CoronaDataLive.class, URL2, header);
        List<CoronaDataLive> processedData = process.processData(coronaDataLive);
        processedData.forEach(System.out::println);

        CoronaDataSummary coronaDataSummary = json.toObjectFromRest(CoronaDataSummary.class, URL1, header);
        String value = json.toJsonString(coronaDataSummary);
        System.out.println(value);
    }

    @Override
    protected ParserName getParserName() {
        return ParserName.Json;
    }
}
