package gr.nikolis.sql.services;

import gr.nikolis.sql.dao.Specie;
import gr.nikolis.handlers.exception.ConflictException;
import gr.nikolis.sql.repositories.SpecieRepository;
import gr.nikolis.utils.MessageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class SpecieService implements IService<Specie> {

    @Autowired
    private SpecieRepository specieRepository;

    @Override
    public Collection<Specie> findAll() {
        return specieRepository.findAll();
    }

    @Override
    public List<Specie> findByName(String name) {
        return null;
    }

    @Override
    public Specie findById(Long id) {
        return specieRepository.findById(id).orElse(null);
    }

    @Override
    public Specie saveOrUpdate(Specie specie) {
        try {
            return specieRepository.saveAndFlush(specie);
        } catch (Exception ex) {
            throw new ConflictException("Conflict on SQL Statement while trying to save object!!");
        }
    }

    @Override
    public MessageBean deleteById(Long id) {
        return deleteById(specieRepository, id);
    }
}
