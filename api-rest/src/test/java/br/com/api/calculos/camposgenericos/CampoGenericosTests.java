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
import br.com.api.calculos.vo.PaginateParansVO;

@SpringBootTest
@ActiveProfiles("development")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
public class CampoGenericosTests {
 
    @Autowired
    LocalValidatorFactoryBean validator;

    @Test
    public void testIdEmpty(){
        GenericParamIDVO idVOVazio = new GenericParamIDVO("");
        assertTrue(!validator.validate(idVOVazio).isEmpty());
    }

    @Test
    public void testIdNull(){
        GenericParamIDVO idVONull = new GenericParamIDVO(null);
        assertTrue(!validator.validate(idVONull).isEmpty());
    }

    @Test
    public void idValido(){
        GenericParamIDVO idVOValido = new GenericParamIDVO("123");
        assertTrue(validator.validate(idVOValido).isEmpty());
    }

    @Test
    public void paginacaoInvalida(){
        
        PaginateParansVO voPag = new PaginateParansVO(null, null);
        assertTrue(!validator.validate(voPag).isEmpty());

        voPag.setLimite("");
        voPag.setPage("");

        assertTrue(!validator.validate(voPag).isEmpty());

    }

    @Test
    public void paginacaoPageInvalido(){
        
        PaginateParansVO voPag = new PaginateParansVO(null, "10");
        assertTrue(!validator.validate(voPag).isEmpty());

        voPag.setPage("");
        assertTrue(!validator.validate(voPag).isEmpty());

    }

    @Test
    public void paginacaoLimitInvalido(){
        
        PaginateParansVO voPag = new PaginateParansVO("10", null);
        assertTrue(!validator.validate(voPag).isEmpty());

        voPag.setLimite("");
        assertTrue(!validator.validate(voPag).isEmpty());

    }

    @Test
    public void paginacaoValida(){
        PaginateParansVO voPag = new PaginateParansVO("1","10");
        assertTrue(validator.validate(voPag).isEmpty());
    }

}
