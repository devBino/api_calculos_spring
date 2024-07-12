package br.com.api.calculos.calculos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import br.com.api.calculos.config.CalculoTestConfig;
import br.com.api.calculos.config.TestConfig;
import br.com.api.calculos.type.SinalCalculoType;

/**
 * Provê testes dos sinais dos calculos
 */
@SpringBootTest
@ActiveProfiles("development")
@ContextConfiguration(classes = {TestConfig.class, CalculoTestConfig.class})
public class SinalCalculoTypeTests {
    
    @Test
    public void retornarValorDefaultSeSinalErrado(){
        assertNull( recuperarSinal("##") );
    }

    @Test
    public void retornaValorSinalAdicao(){
        assertNotNull( recuperarSinal("adi") );
    }

    @Test
    public void retornaValorSinalSubtracao(){
        assertNotNull( recuperarSinal("sub") );
    }

    @Test
    public void retornaValorSinalMultiplicacao(){
        assertNotNull( recuperarSinal("mul") );
    }

    @Test
    public void retornaValorSinalDivisao(){
        assertNotNull( recuperarSinal("div") );
    }

    private SinalCalculoType recuperarSinal(final String sinal){
        return SinalCalculoType.fromCodigo(sinal);
    }

}
