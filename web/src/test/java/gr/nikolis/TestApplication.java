package gr.nikolis;

import gr.nikolis.sql.services.AnimalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@Slf4j
public class TestApplication {
    public static final String URL = "https://localhost:8888/myZoo/animals/all";

    @Autowired
    AnimalService animalService;
    @Test
    public void testing() {
        method1();
    }

    public void method1() {
        var f1 = CompletableFuture.supplyAsync(() -> animalService.learnTrick(2L));
        var f2 = CompletableFuture.supplyAsync(() -> animalService.fillSpeciesList());
        var f3 = CompletableFuture.supplyAsync(() -> animalService.groupAnimals());
        var f4 = CompletableFuture.supplyAsync(() -> animalService.asyncMethod());

        CompletableFuture<Void> all1 = CompletableFuture.allOf(f1,f2, f3);
        try {
            all1.join();
            all1.thenRun(() -> {
                try {
                    var fdsf = f1.get();
                    var sad = f2.get();
                    var fsadfa = f3.get();
                    var sadsad = f4.get();
                    log.info(fdsf);
                    sad.forEach(animal -> {log.info(animal.getName());});
                    log.info(fsadfa.toString());
                    log.info(sadsad);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }

                log.info("All OK");
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void method2() {
        var f1 = CompletableFuture.supplyAsync(() -> animalService.learnTrick(2L));
        var f2 = CompletableFuture.supplyAsync(() -> animalService.fillSpeciesList());
        var f3 = CompletableFuture.supplyAsync(() -> animalService.groupAnimals());

        CompletableFuture.allOf(f1,f2, f3).join();
        var first = f1.join();
        var second = f2.join();
        var third = f3.join();

        log.info(first);
        second.forEach(animal -> {log.info(animal.getName());});
        log.info(third.toString());
    }
}
