package gr.nikolis.parsers.csv;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class Csv {

    @Autowired
    private CsvMapper mapper;

    /**
     * Read the values from csv file (with headers)
     * Serialize the value to the given object
     *
     * @param type     The class type to serialize
     * @param fileName The filename with the suffix (e.x. username.csv)
     * @param <T>      The object to Serialize
     * @return The list with csv elements
     */
    public <T> List<T> toListFromFile(Class<T> type, String fileName) {
        CsvSchema schema = CsvSchema.emptySchema().withHeader().withColumnSeparator(',');
        try (InputStream inputStream = new ClassPathResource(fileName).getInputStream()) {
            MappingIterator<T> readValues = mapper
                    .readerFor(type)
                    .with(schema)
                    .readValues(inputStream);
            return readValues.readAll();
        } catch (JsonProcessingException je) {
            log.error("Error occurred while Serializing object from xml string value " + je);
            return Collections.emptyList();
        } catch (IOException io) {
            log.error("Error occurred while reading from file " + fileName, io);
            return Collections.emptyList();
        }
    }

    /**
     * Write into CSV file the object values (the file is being written on user home folder)
     * Deserialize the values
     *
     * @param type     The type of class
     * @param value    The object values
     * @param filename The filename to write
     * @param <T>      The Type of class object
     */
    public <T> void toCsvFile(Class<T> type, Object value, String filename) {
        try {
            String userHomeFolder = System.getProperty("user.home");
            File textFile = new File(userHomeFolder, filename);
            final CsvSchema schema = mapper.schemaFor(type).withColumnSeparator(',');
            mapper
                    .writer(schema.withUseHeader(true))
                    .writeValue(textFile, value);
        } catch (IOException ioException) {
            log.error("Error occurred while writing object list into the file ", ioException);
        }
    }

    public List<String[]> loadManyToManyRelationship(String fileName) {
        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withSkipFirstDataRow(true);
        mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
        try (InputStream inputStream = new ClassPathResource(fileName).getInputStream()) {
            MappingIterator<String[]> readValues = mapper
                    .readerFor(String[].class)
                    .with(bootstrapSchema)
                    .readValues(inputStream);
            return readValues.readAll();
        } catch (Exception e) {
            log.error("Error occurred while loading many to many relationship from file = " + fileName, e);
            return Collections.emptyList();
        }
    }
}
