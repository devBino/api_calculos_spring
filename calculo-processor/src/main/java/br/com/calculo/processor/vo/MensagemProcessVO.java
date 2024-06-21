package br.com.calculo.processor.vo;

import br.com.calculo.processor.type.MensagemHistoricoType;

/**
 * Encapsula dados das mensagem que devem 
 * ser criadas durante processamento de calculos individuais
 * ou anexos recebidos pela api
 */
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

    public byte getCodigoTipo() {
        return codigoTipo;
    }

    public void setCodigoTipo(MensagemHistoricoType histType) {
        this.codigoTipo = histType.getCodigoTipoMensagem();
    }

    public String getMensagem() {
        return mensagem;
    }
    
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public long getRegistroId() {
        return registroId;
    }
    
    public void setRegistroId(int registroId) {
        this.registroId = registroId;
    }

}
