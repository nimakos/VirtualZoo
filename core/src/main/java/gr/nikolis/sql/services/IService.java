package gr.nikolis.sql.services;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface IService<T> {
    /**
     * Find all collection of elements
     *
     * @return The collection
     */
    Collection<T> findAll();

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
    T saveOrUpdate(T t);

    /**
     * Delete an object by its id
     *
     * @param id The object id
     * @return A JSon object message if object was deleted successfully or not
     */
    String deleteById(Long id);

    /**
     * Overload method for deleting an object
     *
     * @param repository The repository that the object belongs to
     * @param id         The element id
     * @return JSon object message if object was deleted successfully or not
     */
    @Transactional
    default <M, ID> String deleteById(JpaRepository<M, ID> repository, ID id) {
        JSONObject jsonObject = new JSONObject();
        try {
            repository.deleteById(id);
            jsonObject.put("message", "Object deleted successfully");
        } catch (Exception e) {
            try {
                jsonObject.put("message", e.getMessage());
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
        }
        return jsonObject.toString();
    }
}
