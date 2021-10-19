package gr.nikolis.parsers.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * we can always do :  mapper.readValue(summaryStr, new TypeReference<>() {});
 * to get anything we want
 */
@Component
@Slf4j
public class Xml {

    @Autowired
    private XmlMapper mapper;

    public <T> T toObjectFromString(Class<T> typeClass, String value) {
        try {
            return mapper.readValue(value, typeClass);
        } catch (JsonProcessingException e) {
            log.error("Error occurred while Serializing object from xml string value " + value + e);
            return null;
        }
    }

    public <T> T toObjectFromFile(Class<T> typeClass, String fileName) {
        try (InputStream inputStream = new ClassPathResource(fileName).getInputStream()) {
            return mapper.readValue(inputStream, typeClass);
        } catch (JsonProcessingException e) {
            log.error("Error occurred while Serializing object from xml string value " + e);
            return null;
        } catch (FileNotFoundException e) {
            log.error("File not found : " + e);
            return null;
        } catch (IOException ioException) {
            log.error("Error occurred while reading file " + ioException);
            return null;
        }
    }

    public <T> String toXmlString(T value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.error("Error occurred while trying to fetch data" + e);
            return "";
        }
    }
}
