package br.com.api.calculos.calculos;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import br.com.api.calculos.config.CalculoTestConfig;
import br.com.api.calculos.config.TestConfig;
import br.com.api.calculos.vo.CalculoVO;
import jakarta.validation.ConstraintViolation;

@SpringBootTest
@ActiveProfiles("development")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class, CalculoTestConfig.class})
public class CalculosValidacaoParamsTest {
    
    @Autowired
    LocalValidatorFactoryBean validator;

    @Test
    public void validarCalculoVOVazio(){
        final CalculoVO vo = new CalculoVO();
        Set<ConstraintViolation<CalculoVO>> res = validator.validate(vo);
        assertTrue(!res.isEmpty());
    }

    @Test
    public void validarCalculoVOCamposObrigatorios(){
        
        final CalculoVO vo = new CalculoVO();
        
        vo.setNumero1(1.0);
        vo.setNumero2(2.0);
        vo.setSinal('*');
        
        Set<ConstraintViolation<CalculoVO>> res = validator.validate(vo);

        assertTrue(res.isEmpty());

    }

}
