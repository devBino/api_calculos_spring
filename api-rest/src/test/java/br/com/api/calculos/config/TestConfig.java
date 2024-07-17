package br.com.api.calculos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import br.com.api.calculos.domain.converter.AnexoConverter;
import br.com.api.calculos.domain.converter.AnexoHistoricoConverter;
import br.com.api.calculos.domain.converter.CalculoConverter;
import br.com.api.calculos.domain.converter.CalculoHistoricoConverter;

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

    @Bean
    @Primary
    public CalculoConverter calculoConverter(){
        return new CalculoConverter();
    }

    @Bean
    @Primary
    public AnexoConverter anexoConverter(){
        return new AnexoConverter();
    }

    @Bean
    @Primary
    public CalculoHistoricoConverter calculoHistoricoConverter(){
        return new CalculoHistoricoConverter();
    }

    @Bean
    @Primary
    public AnexoHistoricoConverter anexoHistoricoConverter(){
        return new AnexoHistoricoConverter();
    }

}
