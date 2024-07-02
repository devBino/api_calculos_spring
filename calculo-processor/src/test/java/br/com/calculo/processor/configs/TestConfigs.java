package br.com.calculo.processor.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import br.com.calculo.processor.business.AnexoBusiness;
import br.com.calculo.processor.business.CalculoBusiness;
import br.com.calculo.processor.service.CalculoServiceFactory;

/**
 * Configurações gerais para os testes
 */
@Configuration
public class TestConfigs {
    
    @Bean
    @Primary
    public CalculoBusiness calculoBusiness(){
        return new CalculoBusiness();
    }

    @Bean
    @Primary
    public AnexoBusiness anexoBusiness(){
        return new AnexoBusiness();
    }

    @Bean
    @Primary
    public CalculoServiceFactory calculoServiceFactory(){
        return new CalculoServiceFactory();
    }

}
