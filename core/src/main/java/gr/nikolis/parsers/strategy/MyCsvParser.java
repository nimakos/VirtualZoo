package gr.nikolis.parsers.strategy;


import gr.nikolis.parsers.csv.Csv;
import gr.nikolis.parsers.csv.CsvModel;
import gr.nikolis.parsers.enums.ParserName;
import gr.nikolis.parsers.factory.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class MyCsvParser extends Parser {

    @Autowired
    private Csv csv;

    @Override
    public void run() {
        List<CsvModel> csvModelList = csv.toList(CsvModel.class, "files/username.csv");
        csv.toCsv(CsvModel.class, csvModelList, "final.csv");
    }

    @Override
    protected ParserName getParserName() {
        return ParserName.Csv;
    }
}
