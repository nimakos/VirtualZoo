package gr.nikolis.parsers.factory;

import gr.nikolis.parsers.enums.ParserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class ParserFactory {
    private Map<ParserName, Parser> parsers;

    @Autowired
    public ParserFactory(Set<Parser> parserSet) {
        createParser(parserSet);
    }

    public Parser findParser(ParserName parserName) {
        return parsers.get(parserName);
    }

    private void createParser(Set<Parser> parserSet) {
        parsers = new HashMap<>();
        parserSet.forEach(parser -> parsers.put(parser.getParserName(), parser));
    }
}
