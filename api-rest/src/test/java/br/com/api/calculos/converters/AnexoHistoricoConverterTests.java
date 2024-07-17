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
import br.com.api.calculos.domain.converter.AnexoHistoricoConverter;
import br.com.api.calculos.domain.model.MAnexo;
import br.com.api.calculos.domain.model.MAnexoHistorico;
import br.com.api.calculos.domain.vo.AnexoHistoricoVO;

/**
 * Provê testes para conversão entre VO e Model para AnexosHistoricos
 */
@SpringBootTest
@ActiveProfiles("development")
@ContextConfiguration(classes = {TestConfig.class})
public class AnexoHistoricoConverterTests {
    
    @Autowired
    private AnexoHistoricoConverter converter;

    private MAnexoHistorico model;
    private AnexoHistoricoVO vo;

    @BeforeEach
    public void preTests(){

        model = new MAnexoHistorico();

        model.setId(123L);
        model.setAnexo(new MAnexo());
        model.setDescricao("Aguardando Processamento");
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
