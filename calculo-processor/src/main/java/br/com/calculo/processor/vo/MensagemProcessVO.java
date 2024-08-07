package br.com.calculo.processor.vo;

import br.com.calculo.processor.type.MensagemHistoricoType;
import lombok.Getter;
import lombok.Setter;

/**
 * Encapsula dados das mensagem que devem 
 * ser criadas durante processamento de calculos individuais
 * ou anexos recebidos pela api
 */
@Getter
@Setter
public class MensagemProcessVO {
    
    private byte codigoTipo;
    private String mensagem;
    private long registroId;

    public MensagemProcessVO(final MensagemHistoricoType histType, 
        final String mensagem, final long registroId){
    	
        this.codigoTipo = histType.getCodigoTipoMensagem();
        this.mensagem = mensagem;
        this.registroId = registroId;
        
    }
    
    @Override
	public String toString() {
		
    	return new StringBuilder()
    			.append("MensagemProcessVO[codigoTipo=")
    			.append(codigoTipo)
    			.append(", mensagem=")
    			.append(mensagem)
    			.append(", registroId=")
    			.append(registroId)
    			.append("]")
    			.toString();
    	
	}

}
