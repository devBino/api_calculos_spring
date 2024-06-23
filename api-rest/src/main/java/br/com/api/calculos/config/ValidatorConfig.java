package br.com.api.calculos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Configuração do Validator dos VOs e Parâmetros
 * recebidos nas requests
 */
@Configuration
public class ValidatorConfig {
    
    @Bean
    public LocalValidatorFactoryBean validator(){

        ResourceBundleMessageSource msg = new ResourceBundleMessageSource();
        msg.setBasename("Mensagem Validação de Dados");

        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(msg);
        return bean;

    }

}
