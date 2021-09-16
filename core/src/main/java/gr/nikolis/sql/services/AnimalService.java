package gr.nikolis.sql.services;

import gr.nikolis.sql.entities.Animal;
import gr.nikolis.sql.entities.Specie;
import gr.nikolis.sql.entities.Trick;
import gr.nikolis.sql.exceptions.AnimalNotFoundException;
import gr.nikolis.sql.exceptions.ConflictException;
import gr.nikolis.sql.repositories.AnimalRepository;
import gr.nikolis.sql.repositories.SpecieRepository;
import gr.nikolis.utils.MessageBean;
import gr.nikolis.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
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

    private List<Animal> findAllByStream() {
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
            throw new ConflictException("Conflict on SQL Statement while trying to save object!!");
        }
    }

    @Override
    public MessageBean deleteById(Long id) {
        return deleteById(animalRepository, id);
    }

    @Transactional(readOnly = true)
    public String testing() {
        /*var f1 = CompletableFuture.supplyAsync(() -> learnTrick(2L));
        var f2 = CompletableFuture.supplyAsync(this::fillSpeciesList);

        CompletableFuture<Void> all1 = CompletableFuture.allOf(f1, f2);
        try {
            var result = all1.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        CompletableFuture<Void> all = CompletableFuture.allOf(
                Collections
                        .nCopies(2, f1)
                        .stream()
                        .map(f -> f.thenAcceptAsync(foo -> fillSpeciesList()))
                        .toArray(CompletableFuture<?>[]::new));*/
        return "";
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
        List<Animal> animalList = new ArrayList<>(findAllByStream());
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