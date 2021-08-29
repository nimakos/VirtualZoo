package gr.nikolis.utils;

import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class Utils {

    /**
     * Gets a random element from a collection
     *
     * @param coll The collection
     * @param <T>  Type of the collection
     * @return The element fo the collection
     */
    public <T> T random(Collection<T> coll) {
        int num = (int) (Math.random() * coll.size());
        for (T t : coll) if (--num < 0) return t;
        return null;
    }
}
