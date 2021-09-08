package gr.nikolis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestApplication {
    public static final String URL = "https://localhost:8888/myZoo/animals/all";

    /*@Autowired
    private JsonParser jsonParser;*/

    @Test
    public void testing() {
/*        try {
            List<Animal> data = jsonParser.toList(Animal.class, URL, "");
            System.out.println(data);
        } catch (Exception ex) {
            System.out.println(ex);
        }*/
    }
}
