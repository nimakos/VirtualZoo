package gr.nikolis;

import gr.nikolis.utils.DBSeeders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebMain implements CommandLineRunner {

    @Autowired
    private DBSeeders dbSeeders;

    public static void main(String[] args) {
        SpringApplication.run(WebMain.class, args);
    }

    @Override
    public void run(String... args) {
        dbSeeders.run();
    }
}
