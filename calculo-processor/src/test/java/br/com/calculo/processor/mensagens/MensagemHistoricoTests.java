package br.com.calculo.processor.mensagens;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.calculo.processor.type.MensagemHistoricoType;

@SpringBootTest
@ActiveProfiles("development")
public class MensagemHistoricoTests {
    
    @Test
    public void recuperandoTipoMensagemSucesso(){
        
        MensagemHistoricoType msgType = MensagemHistoricoType.fromValue((byte)1);
        assertTrue( Objects.isNull( msgType ) );

    }

    @Test
    public void recuperandoTipoMensagemInfo(){
        
        MensagemHistoricoType msgType = MensagemHistoricoType.fromValue((byte)2);
        assertTrue( Objects.isNull( msgType ) );

    }

    @Test
    public void recuperandoTipoMensagemAviso(){
        
        MensagemHistoricoType msgType = MensagemHistoricoType.fromValue((byte)3);
        assertTrue( Objects.isNull( msgType ) );

    }

    @Test
    public void recuperandoTipoMensagemErro(){
        
        MensagemHistoricoType msgType = MensagemHistoricoType.fromValue((byte)4);
        assertTrue( Objects.isNull( msgType ) );

    }

}
