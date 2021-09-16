package gr.nikolis.controller;

import gr.nikolis.mappings.AnimalMappings;
import gr.nikolis.sql.entities.Animal;
import gr.nikolis.sql.services.AnimalService;
import gr.nikolis.utils.MessageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(AnimalMappings.ANIMALS)
public class AnimalController {
    @Autowired
    private AnimalService animalService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public List<Animal> getAnimals() {
/*
        var f1 = CompletableFuture.supplyAsync(() -> learnTrick(2L));
        //var f2 = CompletableFuture.supplyAsync(() -> animalService.fillSpeciesList());
        var f3 = CompletableFuture.supplyAsync(() -> animalService.groupAnimals());

        CompletableFuture<Void> all1 = CompletableFuture.allOf(f1, f3);
        try {
            all1.thenRun(() -> {

            });
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        return animalService.fillSpeciesList();
    }

    @RequestMapping(value = "/group", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getGroupedAnimals() {
        return animalService.groupAnimals();
    }

    @RequestMapping(value = "/{animalId}/doTrick", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getTrick(@PathVariable(value = "animalId") long animalId) {
        return animalService.animalRandomTrick(animalId);
    }

    @RequestMapping(value = "/{animalId}/learnTrick", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String learnTrick(@PathVariable(value = "animalId") long animalId) {
        return animalService.learnTrick(animalId);
    }

    @RequestMapping(value = "/{animalId}/deleteAnimal", method = RequestMethod.DELETE)
    public MessageBean deleteAnimal(@PathVariable(value = "animalId") long animalId) {
        return animalService.deleteById(animalId);
    }
}
