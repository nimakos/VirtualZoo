package gr.nikolis.sql.seeders;

import gr.nikolis.sql.enums.TrickEnum;
import gr.nikolis.sql.dao.Animal;
import gr.nikolis.sql.dao.Trick;
import gr.nikolis.sql.services.IService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AnimalSeeder implements ISeeder<Animal>{
    @Override
    @Async
    public void seed(IService<Animal> iService) {
        if (iService.findAll().isEmpty()) {
            Set<Trick> oscarTricks = new HashSet<>();
            oscarTricks.add(new Trick(TrickEnum.walksOnLaptop.getCode(), TrickEnum.walksOnLaptop.getName()));

            Set<Trick> brownieTricks = new HashSet<>();
            brownieTricks.add(new Trick(TrickEnum.jumps.getCode(), TrickEnum.jumps.getName()));
            brownieTricks.add(new Trick(TrickEnum.rollsOver.getCode(), TrickEnum.rollsOver.getName()));
            brownieTricks.add(new Trick(TrickEnum.barks.getCode(), TrickEnum.barks.getName()));

            Set<Trick> cookieTricks = new HashSet<>();
            cookieTricks.add(new Trick(TrickEnum.jumps.getCode(), TrickEnum.jumps.getName()));
            cookieTricks.add(new Trick(TrickEnum.barks.getCode(), TrickEnum.barks.getName()));

            Set<Trick> goldieTricks = new HashSet<>();
            goldieTricks.add(new Trick(TrickEnum.none.getCode(), TrickEnum.none.getName()));

            iService.saveOrUpdate(new Animal("Oscar", "cat", oscarTricks));
            iService.saveOrUpdate(new Animal("Brownie", "dog", brownieTricks));
            iService.saveOrUpdate(new Animal("Cookie", "dog", cookieTricks));
            iService.saveOrUpdate(new Animal("Goldie", "fish", goldieTricks));
        }
    }
}
