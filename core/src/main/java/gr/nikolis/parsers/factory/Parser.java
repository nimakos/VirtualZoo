package gr.nikolis.parsers.factory;

import gr.nikolis.parsers.enums.ParserName;
import org.springframework.scheduling.annotation.Async;

public abstract class Parser {
    @Async
    public abstract void run();
    protected abstract ParserName getParserName();
}
