package gr.nikolis.sql.services;

import gr.nikolis.sql.dao.Trick;
import gr.nikolis.handlers.exception.ConflictException;
import gr.nikolis.sql.repositories.TrickRepository;
import gr.nikolis.utils.MessageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class TrickService implements IService<Trick> {
    @Autowired
    private TrickRepository trickRepository;

    @Override
    public Collection<Trick> findAll() {
        return trickRepository.findAll();
    }

    @Override
    public List<Trick> findByName(String name) {
        return null;
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
            throw new ConflictException("Conflict on SQL Statement while trying to save object!!");
        }
    }

    @Override
    public MessageBean deleteById(Long id) {
        return deleteById(trickRepository, id);
    }
}
