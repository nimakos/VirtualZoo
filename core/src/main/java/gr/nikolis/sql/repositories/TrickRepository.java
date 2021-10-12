package gr.nikolis.sql.repositories;

import gr.nikolis.sql.dao.Trick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrickRepository extends JpaRepository<Trick, Long> {
}
