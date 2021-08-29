package gr.nikolis.sql.seeders;

import gr.nikolis.sql.services.IService;

public interface ISeeder<T> {
    /**
     * Inserts dummy data for testing purposes
     *
     * @param iService The generic IService object
     */
    void seed(IService<T> iService);
}
