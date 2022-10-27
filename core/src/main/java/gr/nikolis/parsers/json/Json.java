package gr.nikolis.parsers.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class Json {

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private HttpCall httpCall;

    /**
     * Make Rest api call
     * Serialize the response from Json string to List of object
     *
     * @param typeClass The class to convert
     * @param url       the url to Api call
     * @param header    The header if exists
     * @param <T>       The object to Serialize
     * @return List of object
     */
    public <T> List<T> toListFromRest(Class<T> typeClass, String url, String... header) {
        try {
            String fetchedData = httpCall.get(url, header);
            CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, typeClass);
            return mapper.readValue(fetchedData, listType);
        } catch (IOException ex) {
            log.error("Error occurred while Serializing object from json string value" + ex);
            return Collections.emptyList();
        } catch (InterruptedException ex) {
            log.error("Error occurred while trying to fetch data" + ex);
            return Collections.emptyList();
        }
    }

    /**
     * Make Rest api call
     * Serialize the response from Json string to object
     *
     * @param elementClass The class to convert
     * @param url          the url to Api call
     * @param header       The header if exists
     * @param <T>          The object to Serialize
     * @return The serialized object
     */
    public <T> T toObjectFromRest(Class<T> elementClass, String url, String... header) {
        try {
            String fetchedData = httpCall.get(url, header);
            return mapper.readValue(fetchedData, elementClass);
        } catch (IOException ex) {
            log.error("Error occurred while Serializing object from json string value" + ex);
            return null;
        } catch (InterruptedException ex) {
            log.error("Error occurred while trying to fetch data" + ex);
            return null;
        }
    }

    /**
     * Deserialize the value from object to Json string
     *
     * @param value The given object value
     * @param <T>   The Generic object
     * @return The Deserialized string
     */
    public <T> String toJsonString(T value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.error("Error occurred while trying to fetch data" + e);
            return "";
        }
    }
}
