package br.com.api.calculos.camposgenericos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import br.com.api.calculos.config.TestConfig;
import br.com.api.calculos.vo.GenericParamIDVO;

@SpringBootTest
@ActiveProfiles("development")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
public class CampoGenericosTests {
 
    @Autowired
    LocalValidatorFactoryBean validator;

    @Test
    public void idInvalido(){
        
        GenericParamIDVO idVONull = new GenericParamIDVO(null);
        GenericParamIDVO idVOVazio = new GenericParamIDVO("");

        assertTrue(!validator.validate(idVONull).isEmpty());
        assertTrue(!validator.validate(idVOVazio).isEmpty());

    }

    @Test
    public void idValido(){
        GenericParamIDVO idVOValido = new GenericParamIDVO("123");
        assertTrue(validator.validate(idVOValido).isEmpty());
    }

}
