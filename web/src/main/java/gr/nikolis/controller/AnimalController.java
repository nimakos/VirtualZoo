package gr.nikolis.controller;

import gr.nikolis.mappings.AnimalMappings;
import gr.nikolis.sql.dao.Animal;
import gr.nikolis.sql.services.AnimalService;
import gr.nikolis.utils.MessageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AnimalMappings.ANIMALS)
@Slf4j
public class AnimalController {
    @Autowired
    private AnimalService animalService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true) // this works only here
    public List<Animal> getAnimals() {
        return animalService.fillSpeciesList();
    }

    @RequestMapping(value = "/{id}/getAnimal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(value = "Animal", key = "#id") // this works only here
    public Animal getAnimal(@PathVariable(value = "id") long id) {
        return animalService.findById(id);
    }

    @RequestMapping(value = "/setAnimal", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Animal createAnimal(@RequestBody Animal animal) {
        return animalService.saveOrUpdate(animal);
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
