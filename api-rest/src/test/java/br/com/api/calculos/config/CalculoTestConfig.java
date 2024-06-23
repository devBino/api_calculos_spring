package br.com.api.calculos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import br.com.api.calculos.converter.CalculoConverter;
import br.com.api.calculos.vo.CalculoVO;

/**
 * Configuração para validação dos parametros dos VOs durante os testes
 */
@Configuration
public class CalculoTestConfig {
    
    @Bean
    @Primary
    public CalculoVO calculoVO(){
        return new CalculoVO();
    }

    @Bean
    @Primary
    public CalculoConverter calculoConverter(){
        return new CalculoConverter();
    }

}