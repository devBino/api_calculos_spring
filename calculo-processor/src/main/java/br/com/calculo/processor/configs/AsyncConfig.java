package br.com.calculo.processor.configs;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Configuração asyncrona para métodos
 * que podem ser invocados ao mesmo tempo
 * em multithreading
 */
@Configuration
@EnableAsync
public class AsyncConfig {
    
    @Bean(name = "asyncExecutor")
    public Executor asynExecutor(){

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("AsyncProc_Calcs-");
        executor.initialize();
        
        return executor;

    }

}
