package gr.nikolis.sql.services;

import gr.nikolis.sql.entities.Trick;
import gr.nikolis.sql.repositories.TrickRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Service
@Slf4j
public class TrickService implements IService<Trick> {
    @Autowired private TrickRepository trickRepository;

    @Override
    public Collection<Trick> findAll() {
        return trickRepository.findAll();
    }

    @Override
    public Trick findById(Long id) {
        return trickRepository.findById(id).orElse(null);
    }

    @Override
    public Trick saveOrUpdate(Trick trick) {
        try {
            return trickRepository.saveAndFlush(trick);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Conflict on SQL Statement while trying to save object!!");
        }
    }

    @Override
    public String deleteById(Long id) {
        return deleteById(trickRepository, id);
    }
}
