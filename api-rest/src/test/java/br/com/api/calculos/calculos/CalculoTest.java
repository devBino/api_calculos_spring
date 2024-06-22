package br.com.api.calculos.calculos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("development")
public class CalculoTest {
    
    @Test
    public void testeDeH2IdenficadoComSucesso(){
        assertTrue(true);
    }

}
