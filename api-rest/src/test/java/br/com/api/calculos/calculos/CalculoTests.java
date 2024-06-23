package br.com.api.calculos.calculos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.api.calculos.config.CalculoTestConfig;
import br.com.api.calculos.config.TestConfig;
import br.com.api.calculos.converter.CalculoConverter;
import br.com.api.calculos.model.MCalculo;
import br.com.api.calculos.vo.CalculoVO;

@SpringBootTest
@ActiveProfiles("development")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class, CalculoTestConfig.class})
public class CalculoTests {
    
    @Autowired
    private CalculoConverter converter;

    @Test
    public void convertendoCalculoEntidadeParaVO(){
        
        final MCalculo model = new MCalculo();

        model.setCalculoUU(UUID.randomUUID().toString());
        model.setNumero1(10.11);
        model.setNumero2(23.33);
        model.setSinal('+');
        model.setDescricao("Aguardando Processamento");
        model.setResultado(0.0);
        model.setEstado('A');

        final CalculoVO vo = converter.toBo(model);

        assertTrue( !Objects.isNull(vo.getCalculoUU())  );
        assertTrue( !Objects.isNull(vo.getNumero1())  );
        assertTrue( !Objects.isNull(vo.getNumero2())  );
        assertTrue( !Objects.isNull(vo.getSinal())  );
        assertTrue( !Objects.isNull(vo.getDescricao())  );
        assertTrue( !Objects.isNull(vo.getResultado()) );
        assertTrue( !Objects.isNull(vo.getEstado()) );
        
    }

    @Test
    public void convertendoCalculoVOParaEntidade(){
        
        final CalculoVO vo = new CalculoVO();

        vo.setCalculoUU(UUID.randomUUID().toString());
        vo.setNumero1(10.11);
        vo.setNumero2(23.33);
        vo.setSinal('+');
        vo.setDescricao("Aguardando Processamento");
        vo.setResultado(0.0);
        vo.setEstado('A');

        final MCalculo model = converter.toModel(vo);

        assertTrue( !Objects.isNull(model.getCalculoUU())  );
        assertTrue( !Objects.isNull(model.getNumero1())  );
        assertTrue( !Objects.isNull(model.getNumero2())  );
        assertTrue( !Objects.isNull(model.getSinal())  );
        assertTrue( !Objects.isNull(model.getDescricao())  );
        assertTrue( !Objects.isNull(model.getResultado()) );
        assertTrue( !Objects.isNull(model.getEstado()) );

    }

}
