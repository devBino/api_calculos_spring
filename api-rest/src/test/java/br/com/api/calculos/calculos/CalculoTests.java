package br.com.api.calculos.calculos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.api.calculos.converter.CalculoConverter;
import br.com.api.calculos.model.MCalculo;
import br.com.api.calculos.vo.CalculoVO;

@SpringBootTest
@ActiveProfiles("development")
public class CalculoTests {
    
    @Autowired
    private CalculoConverter converter;

    @Test
    public void testeDeH2IdenficadoComSucesso(){
        assertTrue(true);
    }

    @Test
    public void convertendoCalculoEntidadeParaVO(){
        
        final MCalculo mCalculo = new MCalculo();

        mCalculo.setCalculoUU(UUID.randomUUID().toString());
        mCalculo.setNumero1(10.11);
        mCalculo.setNumero2(23.33);
        mCalculo.setSinal('+');
        mCalculo.setDescricao("Aguardando Processamento");
        mCalculo.setResultado(0.0);
        mCalculo.setEstado('A');

        final CalculoVO vo = converter.toBo(mCalculo);

        assertTrue( !Objects.isNull(vo.getCalculoUU())  );
        assertTrue( !Objects.isNull(vo.getNumero1())  );
        assertTrue( !Objects.isNull(vo.getNumero2())  );
        assertTrue( !Objects.isNull(vo.getSinal())  );
        assertTrue( !Objects.isNull(vo.getDescricao())  );
        assertTrue( !Objects.isNull(vo.getResultado()) );
        assertTrue( !Objects.isNull(vo.getEstado()) );
        
    }

}
