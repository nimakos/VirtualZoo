package gr.nikolis.utils;

import gr.nikolis.sql.models.Animal;
import gr.nikolis.sql.models.Specie;
import gr.nikolis.sql.models.Trick;
import gr.nikolis.sql.seeders.ISeeder;
import gr.nikolis.sql.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBSeeders {
    @Autowired private IService<Animal> animalService;
    @Autowired private IService<Trick> trickService;
    @Autowired private IService<Specie> specieService;

    @Autowired private ISeeder<Animal> animalSeeder;
    @Autowired private ISeeder<Trick> trickSeeder;
    @Autowired private ISeeder<Specie> specieSeeder;

    /**
     * Run Sql seeders
     */
    public void run() {
        trickSeeder.seed(trickService);
        specieSeeder.seed(specieService);
        animalSeeder.seed(animalService);
    }
}
