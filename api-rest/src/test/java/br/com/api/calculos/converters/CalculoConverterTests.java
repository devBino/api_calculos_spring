package br.com.api.calculos.converters;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import br.com.api.calculos.config.CalculoTestConfig;
import br.com.api.calculos.config.TestConfig;
import br.com.api.calculos.converter.CalculoConverter;
import br.com.api.calculos.model.MCalculo;
import br.com.api.calculos.vo.CalculoVO;

/**
 * Provê testes para conversão entre VO e Model para Calculos
 */
@SpringBootTest
@ActiveProfiles("development")
@ContextConfiguration(classes = {TestConfig.class, CalculoTestConfig.class})
public class CalculoConverterTests {
    
    @Autowired
    private CalculoConverter converter;

    private MCalculo model, mdConverted;
    private CalculoVO vo, voConverted;

    @BeforeEach
    public void preTests(){

        model = new MCalculo();

        model.setId(123L);
        model.setCalculoUU(UUID.randomUUID().toString());
        model.setNumero1(10.11);
        model.setNumero2(23.33);
        model.setSinal('+');
        model.setDescricao("Aguardando Processamento");
        model.setResultado(model.getNumero1() + model.getNumero2());
        model.setEstado('A');

        vo = new CalculoVO();

        vo.setId(123L);
        vo.setCalculoUU(UUID.randomUUID().toString());
        vo.setNumero1(10.11);
        vo.setNumero2(23.33);
        vo.setSinal('+');
        vo.setDescricao("Aguardando Processamento");
        vo.setResultado(vo.getNumero1() + vo.getNumero2());
        vo.setEstado('A');

        mdConverted = converter.toModel(vo);
        voConverted = converter.toVo(mdConverted);

    }

    @Test
    public void checkIdConvertido(){
        assertTrue( 
            !Objects.isNull(mdConverted.getId()) &&
            !Objects.isNull(voConverted.getId())
        );
    }

    @Test
    public void checkIgualdadeId(){
        assertTrue(
            vo.getId().longValue() == mdConverted.getId().longValue() &&
            model.getId().longValue() == voConverted.getId().longValue()
        );
    }

    @Test
    public void checkCalculoUUConvertido(){
        assertTrue( 
            !Objects.isNull(mdConverted.getCalculoUU()) &&
            !Objects.isNull(voConverted.getCalculoUU())
        );
    }

    @Test
    public void checkNumero1Convertido(){
        assertTrue( 
            !Objects.isNull(mdConverted.getNumero1()) &&
            !Objects.isNull(voConverted.getNumero1())
        );
    }

    @Test
    public void checkIgualdadeNumero1(){
        assertTrue(
            vo.getNumero1().equals( mdConverted.getNumero1() ) &&
            model.getNumero1().equals( voConverted.getNumero1() )
        );
    }

    @Test
    public void checkNumero2Convertido(){
        assertTrue( 
            !Objects.isNull(mdConverted.getNumero2()) &&
            !Objects.isNull(voConverted.getNumero2())
        );
    }

    @Test
    public void checkIgualdadeNumero2(){
        assertTrue(
            vo.getNumero2().equals( mdConverted.getNumero2() ) &&
            model.getNumero2().equals( voConverted.getNumero2() )
        );
    }

    @Test
    public void checkSinalConvertido(){
        assertTrue( 
            !Objects.isNull(mdConverted.getSinal()) &&
            !Objects.isNull(voConverted.getSinal())
        );
    }

    @Test
    public void checkIgualdadeSinal(){
        assertTrue(
            vo.getSinal().equals( mdConverted.getSinal() ) &&
            model.getSinal().equals( voConverted.getSinal() )
        );
    }

    @Test
    public void checkResultadoConvertido(){
        assertTrue( 
            !Objects.isNull(mdConverted.getResultado()) &&
            !Objects.isNull(voConverted.getResultado())
        );
    }

    @Test
    public void checkIgualdadeResultado(){
        assertTrue(
            vo.getResultado().equals( mdConverted.getResultado() ) &&
            model.getResultado().equals( voConverted.getResultado() )
        );
    }

    @Test
    public void checkDescricaoConvertido(){
        assertTrue( 
            !Objects.isNull(mdConverted.getDescricao()) &&
            !Objects.isNull(voConverted.getDescricao())
        );
    }

    @Test
    public void checkIgualdadeDescricao(){
        assertTrue(
            vo.getDescricao().equals( mdConverted.getDescricao() ) &&
            model.getDescricao().equals( voConverted.getDescricao() )
        );
    }

    @Test
    public void checkEstadoConvertido(){
        assertTrue( 
            !Objects.isNull(mdConverted.getEstado()) &&
            !Objects.isNull(voConverted.getEstado())
        );
    }

    @Test
    public void checkIgualdadeEstado(){
        assertTrue(
            vo.getEstado().equals( mdConverted.getEstado() ) &&
            model.getEstado().equals( voConverted.getEstado() )
        );
    }

}
