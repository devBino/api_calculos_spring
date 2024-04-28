package br.com.calculo.processor.bo;

import br.com.calculo.processor.type.MensagemHistoricoType;

/**
 * Encapsula dados da mensagem que deve 
 * ser criada em {@code MCalculoHistorico} e
 * vinculada em {@code MCalculo}
 */
public class MensagemProcessBO {
    
    private byte codigoTipo;
    private String mensagem;

    public MensagemProcessBO(final MensagemHistoricoType histType, final String mensagem){
        this.codigoTipo = histType.getCodigoTipoMensagem();
        this.mensagem = mensagem;
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

}
