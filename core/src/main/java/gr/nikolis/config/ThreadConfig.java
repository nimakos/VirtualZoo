package gr.nikolis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadConfig {
    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    public static final String EXECUTOR = "myExecutor";

    @Bean(EXECUTOR)
    public ThreadPoolTaskExecutor getExecutor() {
        ThreadPoolTaskExecutor  threadPoolExecutor = new ThreadPoolTaskExecutor();
        threadPoolExecutor.setMaxPoolSize(NUMBER_OF_CORES);
        threadPoolExecutor.setCorePoolSize(NUMBER_OF_CORES);
        threadPoolExecutor.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolExecutor.setQueueCapacity(100);
        threadPoolExecutor.setKeepAliveSeconds(10);
        threadPoolExecutor.initialize();
        return threadPoolExecutor;
    }
}
