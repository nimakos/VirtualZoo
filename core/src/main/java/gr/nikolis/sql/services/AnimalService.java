package gr.nikolis.sql.services;

import gr.nikolis.handlers.exceptions.AnimalNotFoundException;
import gr.nikolis.handlers.exceptions.ConflictException;
import gr.nikolis.sql.dao.Animal;
import gr.nikolis.sql.dao.Specie;
import gr.nikolis.sql.dao.Trick;
import gr.nikolis.sql.repositories.AnimalRepository;
import gr.nikolis.sql.repositories.SpecieRepository;
import gr.nikolis.utils.MessageBean;
import gr.nikolis.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class AnimalService implements IService<Animal> {

    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private SpecieRepository specieRepository;
    @Autowired
    private Utils utils;

    @Override
    public Collection<Animal> findAll() {
        return animalRepository.findAll();
    }

    public List<Animal> findAllByStream() {
        try (Stream<Animal> animalStream = animalRepository.findAllByStream()) {
            return animalStream.parallel().collect(Collectors.toList());
        }
    }

    @Override
    public List<Animal> findByName(String name) {
        try (Stream<Animal> animalStream = animalRepository.findByName(name)) {
            return animalStream.parallel().collect(Collectors.toList());
        }
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
            throw new ConflictException("Conflict on SQL Statement while trying to save object!! : " + ex.getMessage());
        }
    }

    @Override
    @CacheEvict(value = "animal", key = "#id")
    public MessageBean deleteById(Long id) {
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
        String name = "";
        if (animal != null) {
            Specie specie = specieRepository.findBySpecieType(animal.getSpecie());
            Set<Trick> tricksLearned = new HashSet<>(animal.getTricksSet());
            Set<Trick> tricksToLearn = new HashSet<>(specie.getTricksSet());
            tricksToLearn.removeAll(tricksLearned);
            Trick trickToLearn = utils.random(tricksToLearn);
            if (trickToLearn != null) {
                animal.getTricksSet().add(trickToLearn);
                saveOrUpdate(animal);
                name = trickToLearn.getTrick();
            }
        }
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
        if (animal == null)
            throw new AnimalNotFoundException("id=" + id);
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