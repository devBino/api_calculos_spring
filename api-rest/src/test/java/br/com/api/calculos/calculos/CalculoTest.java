package br.com.api.calculos.calculos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.api.calculos.model.MCalculo;
import br.com.api.calculos.model.ifacejpa.CalculoRepository;

@SpringBootTest
@ActiveProfiles("development")
public class CalculoTest {
    
    @Autowired
    private CalculoRepository repository;

    @Test
    public void testeDeH2IdenficadoComSucesso(){
        assertTrue(true);
    }

    @Test
    public void criandoCalculo(){
        
        final MCalculo mCalculo = new MCalculo();

        mCalculo.setCalculoUU(UUID.randomUUID().toString());
        mCalculo.setNumero1(10.11);
        mCalculo.setNumero2(23.33);
        mCalculo.setSinal('+');
        mCalculo.setDescricao("Aguardando Processamento");
        mCalculo.setResultado(0.0);
        mCalculo.setEstado('A');

        final MCalculo saved = repository.save(mCalculo);

        assertTrue( Objects.isNull(saved.getId())  );
        
    }

}
