package br.com.calculo.processor.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import br.com.calculo.processor.business.CalculoBusiness;

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

}
