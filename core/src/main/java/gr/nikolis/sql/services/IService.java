package gr.nikolis.sql.services;

import gr.nikolis.sql.exceptions.ConflictException;
import gr.nikolis.utils.MessageBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Service
public interface IService<T> {
    /**
     * Find all collection of elements
     *
     * @return The collection
     */
    Collection<T> findAll();

    List<T> findByName(String name);

    /**
     * Find a specific object by id
     *
     * @param id The element id
     * @return The element
     */
    T findById(Long id);

    /**
     * Save or update the object
     *
     * @param t The object to save
     * @return The saved object
     */
    @Transactional
    T saveOrUpdate(T t) throws ConflictException;

    /**
     * Delete an object by its id
     *
     * @param id The object id
     * @return A JSon object message if object was deleted successfully or not
     */
    MessageBean deleteById(Long id);

    /**
     * Overload generic method for deleting an object
     *
     * @param repository The repository that the object belongs to
     * @param id         The element id
     * @return JSon object message if object was deleted successfully or not
     */
    @Transactional
    default <M, ID> MessageBean deleteById(JpaRepository<M, ID> repository, ID id) {
        try {
            repository.deleteById(id);
            return new MessageBean("Object deleted successfully");
        } catch (Exception ex) {
            return new MessageBean("Object not deleted");
        }
    }
}
