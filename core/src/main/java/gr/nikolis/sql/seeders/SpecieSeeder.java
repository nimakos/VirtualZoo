package gr.nikolis.sql.seeders;

import gr.nikolis.sql.enums.SpecieEnum;
import gr.nikolis.sql.enums.TrickEnum;
import gr.nikolis.sql.entities.Specie;
import gr.nikolis.sql.entities.Trick;
import gr.nikolis.sql.services.IService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class SpecieSeeder implements ISeeder<Specie> {
    @Override
    @Async
    public void seed(IService<Specie> iService) {
        if (iService.findAll().isEmpty()) {
            Set<Trick> dogTricks = new HashSet<>();
            dogTricks.add(new Trick(TrickEnum.jumps.getCode(), TrickEnum.jumps.getName()));
            dogTricks.add(new Trick(TrickEnum.rollsOver.getCode(), TrickEnum.rollsOver.getName()));
            dogTricks.add(new Trick(TrickEnum.barks.getCode(), TrickEnum.barks.getName()));
            dogTricks.add(new Trick(TrickEnum.lie.getCode(), TrickEnum.lie.getName()));

            Set<Trick> catTricks = new HashSet<>();
            catTricks.add(new Trick(TrickEnum.walksOnLaptop.getCode(), TrickEnum.walksOnLaptop.getName()));

            Set<Trick> fishTricks = new HashSet<>();
            fishTricks.add(new Trick(TrickEnum.none.getCode(), TrickEnum.none.getName()));

            iService.saveOrUpdate(new Specie(SpecieEnum.dog.getCode(), SpecieEnum.dog.getName(), dogTricks));
            iService.saveOrUpdate(new Specie(SpecieEnum.cat.getCode(), SpecieEnum.cat.getName(), catTricks));
            iService.saveOrUpdate(new Specie(SpecieEnum.fish.getCode(), SpecieEnum.fish.getName(), fishTricks));
        }
    }
}
