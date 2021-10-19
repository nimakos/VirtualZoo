package gr.nikolis;

import gr.nikolis.utils.CoronaVirusSeeder;
import gr.nikolis.utils.DBSeeders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class WebMain implements CommandLineRunner, AsyncConfigurer {

    @Autowired
    private DBSeeders dbSeeders;
    @Autowired
    private CoronaVirusSeeder coronaVirusSeeder;

    public static void main(String[] args) {
        SpringApplication.run(WebMain.class, args);
    }

    @Override
    public void run(String... args) {
        dbSeeders.run();
        coronaVirusSeeder.run();
    }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(10);
        executor.setThreadNamePrefix("DemoExecutor-");
        executor.initialize();
        return executor;
    }
}
