package gr.nikolis.controller;

import gr.nikolis.mappings.AnimalMappings;
import gr.nikolis.sql.models.Animal;
import gr.nikolis.sql.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(AnimalMappings.ANIMALS)
public class AnimalController {
    @Autowired
    private AnimalService animalService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebAsyncTask<List<Animal>> getAnimals() {
        return new WebAsyncTask<>(4000, () -> {
            animalService.fillSpeciesList();
            return new ArrayList<>(animalService.findAll());
        });
    }

    @RequestMapping(value = "/group", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebAsyncTask<List<String>> getGroupedAnimals() {
        return new WebAsyncTask<>(4000, () -> {
            List<Animal> animalList = new ArrayList<>(animalService.findAll());
            Map<String, List<Animal>> animalListGrouped = animalList.stream().collect(Collectors.groupingBy(Animal::getSpecie));
            return new ArrayList<>(animalListGrouped.keySet());
        });
    }

    @RequestMapping(value = "/{animalId}/doTrick", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebAsyncTask<String> getTrick(@PathVariable(value = "animalId") long animalId) {
        return new WebAsyncTask<>(4000, () -> {
            String animalTrick = animalService.animalRandomTrick(animalId);
            return "{ \"trick\": \"" + animalTrick + "\"}";
        });
    }

    @RequestMapping(value = "/{animalId}/learnTrick", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebAsyncTask<String> learnTrick(@PathVariable(value = "animalId") long animalId) {
        return new WebAsyncTask<>(4000, () -> {
            String learnedTrick = animalService.learnTrick(animalId);
            return "{ \"learnedTrick\": \"" + learnedTrick + "\"}";
        });
    }
}
