package gr.nikolis;

import gr.nikolis.sql.services.AnimalService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;

@SpringBootTest
public class TestApplication {
    public static final String URL = "https://localhost:8888/myZoo/animals/all";

    @Autowired
    AnimalService animalService;
    @Test
    public void testing() {
        var f1 = CompletableFuture.supplyAsync(() -> animalService.learnTrick(2L));
        //var f2 = CompletableFuture.supplyAsync(() -> animalService.fillSpeciesList());
        var f3 = CompletableFuture.supplyAsync(() -> animalService.groupAnimals());

        CompletableFuture<Void> all1 = CompletableFuture.allOf(f1, f3);
        try {
            all1.thenRun(() -> {

            });
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
