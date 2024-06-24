package br.com.api.calculos.calculos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import br.com.api.calculos.config.CalculoTestConfig;
import br.com.api.calculos.config.TestConfig;
import br.com.api.calculos.type.SinalCalculoType;

@SpringBootTest
@ActiveProfiles("development")
@ContextConfiguration(classes = {TestConfig.class, CalculoTestConfig.class})
public class SinalCalculoTypeTests {
    
    @Test
    public void retornarValorDefaultSeSinalErrado(){
        assertNull( recuperarSinal((byte)5) );
    }

    @Test
    public void retornaValorSinalAdicao(){
        assertNotNull( recuperarSinal((byte)1) );
    }

    @Test
    public void retornaValorSinalSubtracao(){
        assertNotNull( recuperarSinal((byte)2) );
    }

    @Test
    public void retornaValorSinalMultiplicacao(){
        assertNotNull( recuperarSinal((byte)3) );
    }

    @Test
    public void retornaValorSinalDivisao(){
        assertNotNull( recuperarSinal((byte)4) );
    }

    private SinalCalculoType recuperarSinal(final byte sinal){
        return SinalCalculoType.fromIndice(sinal);
    }

}
