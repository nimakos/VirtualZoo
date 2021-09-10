package gr.nikolis.sql.repositories;

import gr.nikolis.sql.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    @Transactional
    Stream<Animal> findByName(String name);

    @Transactional
    @Query("SELECT a FROM Animal a")
    Stream<Animal> findAllByStream();
}
