package gr.nikolis.sql.seeders;

import gr.nikolis.sql.enums.TrickEnum;
import gr.nikolis.sql.entities.Trick;
import gr.nikolis.sql.services.IService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TrickSeeder implements ISeeder<Trick>{
    @Override
    @Async
    public void seed(IService<Trick> iService) {
        if (iService.findAll().isEmpty()) {
            iService.saveOrUpdate(new Trick(TrickEnum.walksOnLaptop.getCode(), TrickEnum.walksOnLaptop.getName()));
            iService.saveOrUpdate(new Trick(TrickEnum.jumps.getCode(), TrickEnum.jumps.getName()));
            iService.saveOrUpdate(new Trick(TrickEnum.rollsOver.getCode(), TrickEnum.rollsOver.getName()));
            iService.saveOrUpdate(new Trick(TrickEnum.barks.getCode(), TrickEnum.barks.getName()));
            iService.saveOrUpdate(new Trick(TrickEnum.lie.getCode(), TrickEnum.lie.getName()));
            iService.saveOrUpdate(new Trick(TrickEnum.none.getCode(), TrickEnum.none.getName()));
        }
    }
}
