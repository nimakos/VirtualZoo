package gr.nikolis.controller;

import gr.nikolis.mappings.AnimalMappings;
import gr.nikolis.sql.dao.Animal;
import gr.nikolis.sql.services.AnimalService;
import gr.nikolis.utils.MessageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(AnimalMappings.ANIMALS)
@Slf4j
public class AnimalController {
    @Autowired
    private AnimalService animalService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @Transactional(readOnly = true) // this works only here
    public List<Animal> getAnimals() {
        return animalService.fillSpeciesList();
    }

    @RequestMapping(value = "/getAnimal/{id}", method = RequestMethod.GET)
    @Cacheable(value = "Animal", key = "#id") // this works only here
    public Animal getAnimal(@PathVariable(value = "id") long id) {
        return animalService.findById(id);
    }

    @RequestMapping(value = "/setAnimal", method = RequestMethod.POST)
    public ResponseEntity<?> createAnimal(@Valid @RequestBody Animal animal) {
        Animal savedAnimal = animalService.saveOrUpdate(animal);

        //return the URI location of the call to the Header
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAnimal.getId()).toUri();
        return  ResponseEntity.created(location).build();
        //return animalService.saveOrUpdate(animal);
    }

    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public List<String> getGroupedAnimals() {
        return animalService.groupAnimals();
    }

    @RequestMapping(value = "/doTrick/{animalId}", method = RequestMethod.GET)
    public String getTrick(@PathVariable(value = "animalId") long animalId) {
        return animalService.animalRandomTrick(animalId);
    }

    @RequestMapping(value = "/learnTrick/{animalId}", method = RequestMethod.PUT)
    public String learnTrick(@PathVariable(value = "animalId") long animalId) {
        return animalService.learnTrick(animalId);
    }

    @RequestMapping(value = "/deleteAnimal/{animalId}", method = RequestMethod.DELETE)
    public MessageBean deleteAnimal(@PathVariable(value = "animalId") long animalId) {
        return animalService.deleteById(animalId);
    }
}
