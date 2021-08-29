package gr.nikolis.sql.services;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface IService<T> {
    Collection<T> findAll();

    T findById(Long id);

	@Transactional
    T saveOrUpdate(T t);

    String deleteById(Long id);

    default <M, ID> String deleteById(JpaRepository<M, ID> repository, ID id) {
		JSONObject jsonObject = new JSONObject();
		try {
			repository.deleteById(id);
			jsonObject.put("message", "User deleted successfully");
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
