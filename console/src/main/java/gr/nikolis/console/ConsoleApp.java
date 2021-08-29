package gr.nikolis.console;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@Slf4j
//@Component
public class ConsoleApp {

    @Autowired
    public ConsoleApp() {

    }

    // == events ==
    @EventListener(ContextRefreshedEvent.class)
    public void start() {
        
    }
}
