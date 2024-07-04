package br.com.calculo.processor.anexos;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import br.com.calculo.processor.business.AnexoBusiness;
import br.com.calculo.processor.configs.TestConfigs;
import br.com.calculo.processor.model.MCalculo;

@SpringBootTest
@ActiveProfiles("development")
@ContextConfiguration(classes = {TestConfigs.class})
public class AnexoTests {
    
    @Autowired
    private AnexoBusiness anexoBusiness;

    private MCalculo model;
    
    @BeforeEach
    public void preTests(){

        model = new MCalculo();

        model.setId(Long.valueOf("123"));
        model.setNumero1(1.99);
        model.setNumero2(2.34);
        model.setResultado(0.0);
        model.setSinal('+');
        model.setEstado('P');
        model.setDescricao("Aguardando Processamento");
        model.setCalculoUU(UUID.randomUUID().toString());

        anexoBusiness.aplicarCalculo(model, 10L);

    }

    @Test
    public void checkResultadoCalculado(){
        assertTrue( model.getResultado() > 0.0 );
    }

    @Test
    public void checkStatusFinalizado(){
        assertTrue( model.getEstado() == 'F' );
    }

    @Test
    public void checkDescricaoPreenchida(){
        assertTrue( !model.getDescricao().equals("Aguardando Processamento") );
    }

    @Test
    public void divisaoPorZero(){
        
        model.setNumero1(0.0);
        model.setNumero2(0.0);
        model.setSinal('/');
        model.setEstado('A');
        
        try{
            anexoBusiness.aplicarCalculo(model, 10L);
        }catch(final Exception exception){
            assertTrue(false);
        }
        
        assertTrue(!Double.isNaN(model.getResultado()));
        assertTrue(model.getEstado().charValue() == 'F');

    }

    @Test
    public void checkAnexoVinculado(){
        assertTrue( Objects.nonNull( model.getAnexoId() ) );
    }

}
