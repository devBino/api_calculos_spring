package br.com.api.calculos.converters;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import br.com.api.calculos.config.TestConfig;
import br.com.api.calculos.converter.AnexoConverter;
import br.com.api.calculos.model.MAnexo;
import br.com.api.calculos.vo.AnexoVO;

/**
 * Provê testes para conversão entre VO e Model para Anexos
 */
@SpringBootTest
@ActiveProfiles("development")
@ContextConfiguration(classes = {TestConfig.class})
public class AnexoConverterTests {
 
    @Autowired
    private AnexoConverter converter;

    private MAnexo model, mdConverted;
    private AnexoVO vo, voConverted;

    @BeforeEach
    public void preTests(){
        
        model = new MAnexo();

        model.setId(123L);
        model.setName("file_test.csv");
        model.setContentType("text/csv");
        model.setCreatedAt(LocalDateTime.now());
        model.setData("n1;n2;s\n1;2;+".getBytes());
        model.setStatus('A');

        vo = new AnexoVO();

        vo.setId(123L);
        vo.setName("file_test.csv");
        vo.setContentType("text/csv");
        vo.setCreatedAt(LocalDateTime.now());
        vo.setData("n1;n2;s\n1;2;+".getBytes());
        vo.setStatus('A');

        mdConverted = converter.toModel(vo);
        voConverted = converter.toVO(model);

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
    public void checkNameConvertido(){
        assertTrue( 
            !Objects.isNull(mdConverted.getName()) &&
            !Objects.isNull(voConverted.getName())
        );
    }

    @Test
    public void checkIgualdadeName(){
        assertTrue(
            vo.getName().equals( mdConverted.getName() ) &&
            model.getName().equals( voConverted.getName() )
        );
    }

    @Test
    public void checkContentTypeConvertido(){
        assertTrue( 
            !Objects.isNull(mdConverted.getContentType()) &&
            !Objects.isNull(voConverted.getContentType())
        );
    }

    @Test
    public void checkIgualdadeContentType(){
        assertTrue(
            vo.getContentType().equals( mdConverted.getContentType() ) &&
            model.getContentType().equals( voConverted.getContentType() )
        );
    }

    @Test
    public void checkCreatedAtConvertido(){
        assertTrue( 
            !Objects.isNull(mdConverted.getCreatedAt()) &&
            !Objects.isNull(voConverted.getCreatedAt())
        );
    }

    @Test
    public void checkIgualdadeCreatedAt(){
        assertTrue(
            vo.getCreatedAt().equals( mdConverted.getCreatedAt() ) &&
            model.getCreatedAt().equals( voConverted.getCreatedAt() )
        );
    }

    @Test
    public void checkDataConvertido(){
        assertTrue( 
            !Objects.isNull(mdConverted.getData()) &&
            !Objects.isNull(voConverted.getData())
        );
    }

    @Test
    public void checkStatusConvertido(){
        assertTrue( 
            !Objects.isNull(mdConverted.getStatus()) &&
            !Objects.isNull(voConverted.getStatus())
        );
    }

    @Test
    public void checkIgualdadeStatus(){
        assertTrue(
            vo.getStatus().equals( mdConverted.getStatus() ) &&
            model.getStatus().equals( voConverted.getStatus() )
        );
    }
    
}
