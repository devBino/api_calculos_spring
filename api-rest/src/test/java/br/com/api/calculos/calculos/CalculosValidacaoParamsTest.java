package br.com.api.calculos.calculos;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import br.com.api.calculos.config.CalculoTestConfig;
import br.com.api.calculos.config.TestConfig;
import br.com.api.calculos.domain.vo.CalculoVO;

@SpringBootTest
@ActiveProfiles("development")
@ContextConfiguration(classes = {TestConfig.class, CalculoTestConfig.class})
public class CalculosValidacaoParamsTest {
    
    @Autowired
    LocalValidatorFactoryBean validator;

    private CalculoVO emptyVo, fullVo;

    @BeforeEach
    public void preTests(){
        
        emptyVo = new CalculoVO();
        fullVo = new CalculoVO();

        fullVo.setNumero1(1.0);
        fullVo.setNumero2(2.0);
        fullVo.setSinal('*');

    }

    @Test
    public void validarCalculoVOVazio(){
        assertTrue(!validator.validate(emptyVo).isEmpty());
    }

    @Test
    public void validarCalculoVOCamposObrigatorios(){
        assertTrue( validator.validate(fullVo).isEmpty() );
    }

    @Test
    public void validarCampoNumero1Obrigatorio(){
        fullVo.setNumero1(null);
        assertTrue( !validator.validate(fullVo).isEmpty() );
    }

    @Test
    public void validarCampoNumero2Obrigatorio(){
        fullVo.setNumero2(null);
        assertTrue( !validator.validate(fullVo).isEmpty() );
    }

    @Test
    public void validarCampoSinalObrigatorio(){
        fullVo.setSinal(null);
        assertTrue( !validator.validate(fullVo).isEmpty() );
    }

}
