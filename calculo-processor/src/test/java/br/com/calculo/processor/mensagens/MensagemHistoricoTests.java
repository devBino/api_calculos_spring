package br.com.calculo.processor.mensagens;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.calculo.processor.business.CalculoBusiness;
import br.com.calculo.processor.configs.TestConfigs;
import br.com.calculo.processor.model.MCalculo;
import br.com.calculo.processor.type.MensagemHistoricoType;
import br.com.calculo.processor.vo.MensagemProcessVO;

@SpringBootTest
@ActiveProfiles("development")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfigs.class})
public class MensagemHistoricoTests {
    
    @Autowired
    private CalculoBusiness calculoBusiness;

    @Test
    public void recuperandoTipoMensagemSucesso(){
        MensagemHistoricoType msgType = MensagemHistoricoType.fromValue((byte)1);
        assertTrue( !Objects.isNull( msgType ) );
    }

    @Test
    public void recuperandoTipoMensagemInfo(){
        MensagemHistoricoType msgType = MensagemHistoricoType.fromValue((byte)2);
        assertTrue( !Objects.isNull( msgType ) );
    }

    @Test
    public void recuperandoTipoMensagemAviso(){
        MensagemHistoricoType msgType = MensagemHistoricoType.fromValue((byte)3);
        assertTrue( !Objects.isNull( msgType ) );
    }

    @Test
    public void recuperandoTipoMensagemErro(){
        MensagemHistoricoType msgType = MensagemHistoricoType.fromValue((byte)4);
        assertTrue( !Objects.isNull( msgType ) );
    }

    @Test
    public void mensagensProcessamentoCalculo(){

        MCalculo model = new MCalculo();

        model.setId(Long.valueOf("123"));
        model.setNumero1(1.99);
        model.setNumero2(2.34);
        model.setSinal('+');
        model.setEstado('P');
        model.setDescricao("Aguardando Processamento");
        model.setCalculoUU(UUID.randomUUID().toString());

        List<MensagemProcessVO> mensagens = new ArrayList<>();

        calculoBusiness.aplicarCalculo(model, mensagens);
        
        assertTrue(!mensagens.isEmpty());
        assertTrue(model.getEstado().charValue() == 'F');

    }

}
