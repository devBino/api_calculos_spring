package br.com.api.calculos.converters;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import br.com.api.calculos.config.TestConfig;
import br.com.api.calculos.converter.CalculoHistoricoConverter;
import br.com.api.calculos.model.MCalculo;
import br.com.api.calculos.model.MCalculoHistorico;
import br.com.api.calculos.vo.CalculoHistoricoVO;

/**
 * Provê testes para conversão entre VO e Model para CalculosHistoricos
 */
@SpringBootTest
@ActiveProfiles("development")
@ContextConfiguration(classes = {TestConfig.class})
public class CalculoHistoricoConverterTests {
    
    @Autowired
    private CalculoHistoricoConverter converter;

    private MCalculoHistorico model;
    private CalculoHistoricoVO vo;

    @BeforeEach
    public void preTests(){

        model = new MCalculoHistorico();

        model.setId(123L);
        model.setCalculo(new MCalculo());
        model.setDescricao("Processado com sucesso");
        model.setTipo((byte)1);

        vo = converter.toVo(model);
        
    }

    @Test
    public void checkIdConvertido(){
        assertTrue( !Objects.isNull(vo.getId()) );
    }

    @Test
    public void checkIgualdadeId(){
        assertTrue( model.getId().longValue() == vo.getId().longValue() );
    }

    @Test
    public void checkDescricaoConvertido(){
        assertTrue( !Objects.isNull(vo.getDescricao()) );
    }

    @Test
    public void checkIgualdadeDescricao(){
        assertTrue( model.getDescricao().equals(vo.getDescricao()) );
    }

    @Test
    public void chekcTipoConvertido(){
        assertTrue( !Objects.isNull(vo.getTipo()) );
    }

    @Test
    public void checkIgualdadeTipo(){
        assertTrue( model.getTipo().equals( vo.getTipo() ) );
    }

}
