package gr.nikolis.utils;

import gr.nikolis.parsers.enums.ParserName;
import gr.nikolis.parsers.factory.Parser;
import gr.nikolis.parsers.factory.ParserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * CoronaVirus API : https://documenter.getpostman.com/view/10808728/SzS8rjbc#00030720-fae3-4c72-8aea-ad01ba17adf8
 */
@Component
public class CoronaVirusSeeder {

    @Autowired
    private ParserFactory parserFactory;

    public void run() {
        Parser jsonParser = parserFactory.findParser(ParserName.Json);
        jsonParser.run();
        Parser xmlParser = parserFactory.findParser(ParserName.Xml);
        xmlParser.run();
        Parser csvParser = parserFactory.findParser(ParserName.Csv);
        csvParser.run();
    }
}
