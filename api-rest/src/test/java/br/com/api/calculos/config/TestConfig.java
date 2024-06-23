package br.com.api.calculos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Configurações gerais dos testes
 */
@Configuration
public class TestConfig {
   
    @Bean
    @Primary
    public LocalValidatorFactoryBean validator(){
        return new LocalValidatorFactoryBean();
    }

}
