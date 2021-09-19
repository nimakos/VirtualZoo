package gr.nikolis.async;

import gr.nikolis.sql.entities.Animal;
import gr.nikolis.sql.services.AnimalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
@Slf4j
public class AsyncMethods {
    @Autowired
    AnimalService animalService;

    @PostConstruct
    public void init() {
        //method1();
        //List<Animal> animals= animalService.findAllByStream();
        System.out.println();
    }

    public void method1() {
        var f1 = CompletableFuture.supplyAsync(() -> animalService.learnTrick(2L));
        var f2 = CompletableFuture.supplyAsync(() -> animalService.fillSpeciesList());
        var f3 = CompletableFuture.supplyAsync(() -> animalService.groupAnimals());
        var f4 = CompletableFuture.supplyAsync(this::asyncMethod);

        CompletableFuture<Void> all1 = CompletableFuture.allOf(f1, f2, f3);
        try {
            //all1.join();
            all1.thenRun(() -> {
                try {
                    var fdsf = f1.get();
                    var sad = f2.get();
                    var fsadfa = f3.get();
                    var sadsad = f4.get();
                    log.info(fdsf);
                    sad.forEach(animal -> {
                        log.info(animal.getName());
                    });
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
        var f4 = CompletableFuture.supplyAsync(this::asyncMethod);

        CompletableFuture.allOf(f1, f2, f3).join();
        var first = f1.join();
        var second = f2.join();
        var third = f3.join();
        var fourth = f4.join();

        log.info(first);
        second.forEach(animal -> {
            log.info(animal.getName());
        });
        log.info(third.toString());
        log.info(fourth);
    }

    @Async
    public String asyncMethod() {
        try {
            Thread.sleep(10000);
            return "Hallo";
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Vagos";
    }
}
