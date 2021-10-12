package gr.nikolis.sql.repositories;

import gr.nikolis.sql.dao.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Stream<Animal> findByName(String name);

    @Query("SELECT a FROM Animal a")
    Stream<Animal> findAllByStream();
}
