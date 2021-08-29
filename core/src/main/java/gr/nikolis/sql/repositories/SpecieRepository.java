package gr.nikolis.sql.repositories;

import gr.nikolis.sql.models.Specie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecieRepository extends JpaRepository<Specie, Long> {
    Specie findBySpecieType(String specieType);
}
