package gr.nikolis.sql.services;

import gr.nikolis.sql.models.Animal;
import gr.nikolis.sql.models.Specie;
import gr.nikolis.sql.models.Trick;
import gr.nikolis.sql.repositories.AnimalRepository;
import gr.nikolis.sql.repositories.SpecieRepository;
import gr.nikolis.sql.repositories.TrickRepository;
import gr.nikolis.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AnimalService implements IService<Animal> {

    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private TrickRepository trickRepository;
    @Autowired
    private SpecieRepository specieRepository;
    @Autowired
    private Utils utils;

    @Override
    public Collection<Animal> findAll() {
        return animalRepository.findAll();
    }

    @Override
    public Animal findById(Long id) {
        return animalRepository.findById(id).orElse(null);
    }

    @Override
    public Animal saveOrUpdate(Animal animal) {
        try {
            return animalRepository.saveAndFlush(animal);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Conflict on SQL Statement while trying to save object!!");
        }
    }

    @Override
    public String deleteById(Long id) {
        return deleteById(animalRepository, id);
    }

    /**
     * Learn to an animal a new trick
     *
     * @param animalId The animal ID
     * @return The animal object
     */
    @Async
    public String learnTrick(Long animalId) {
        Animal animal = findById(animalId);
        Specie specie = specieRepository.findBySpecieType(animal.getSpecie());
        Set<Trick> tricksLearned = new HashSet<>(animal.getTricksSet());
        Set<Trick> tricksToLearn = new HashSet<>(specie.getTricksSet());
        tricksToLearn.removeAll(tricksLearned);
        Trick trickToLearn = utils.random(tricksToLearn);
        if (trickToLearn != null) {
            animal.getTricksSet().add(trickToLearn);
            saveOrUpdate(animal);
        }
        String name = trickToLearn != null ? trickToLearn.getTrick() : "";
        return "{ \"learnedTrick\": \"" + name + "\"}";
    }

    /**
     * Fill the String list of species object
     * in order to show on json view
     */
    @Async
    public List<Animal> fillSpeciesList() {
        List<Animal> animalList = new ArrayList<>(findAll());
        for (Animal animal : animalList) {
            Set<Trick> trk = animal.getTricksSet();
            List<String> trkName = new ArrayList<>();
            trk.forEach(trick -> {
                String trickName = trick.getTrick().isEmpty() ? "None" : trick.getTrick();
                trkName.add(trickName);
            });
            animal.setTricks(trkName);
        }
        return new ArrayList<>(findAll());
    }

    /**
     * Do a random trick
     *
     * @param id The animal id
     * @return The trick
     */
    @Async
    public String animalRandomTrick(Long id) {
        Animal animal = findById(id);
        Set<Trick> tricks = animal.getTricksSet();
        return "{ \"trick\": \"" + utils.random(tricks).getTrick() + "\"}";
    }

    /**
     * Grouping the animals
     *
     * @return The list of string of grouped animals
     */
    @Async
    public List<String> groupAnimals() {
        List<Animal> animalList = new ArrayList<>(findAll());
        Map<String, List<Animal>> animalListGrouped = animalList.stream().collect(Collectors.groupingBy(Animal::getSpecie));
        return new ArrayList<>(animalListGrouped.keySet());
    }
}