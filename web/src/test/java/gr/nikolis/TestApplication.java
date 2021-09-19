package gr.nikolis;

import gr.nikolis.sql.services.AnimalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class TestApplication {
    public static final String URL = "https://localhost:8888/myZoo/animals/all";

    @Autowired
    AnimalService animalService;
    @Test
    public void testing() {

    }
}
