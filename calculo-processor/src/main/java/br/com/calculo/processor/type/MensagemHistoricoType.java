package br.com.calculo.processor.type;

/**
 * {@code MensagemHistoricoType} Define os tipos de mensagens que um {@code MCalculoHistorico}
 * podem assumir ao ser vinculado a um {@code MCalculo}
 */
public enum MensagemHistoricoType {
    
    SUCESSO((byte)1),
    INFO((byte)2),
    AVISO((byte)3),
    ERRO((byte)4);

    byte codigoTipoMensagem;

    MensagemHistoricoType(byte codigoTipoMensagem){
        this.codigoTipoMensagem = codigoTipoMensagem;
    }

    public byte getCodigoTipoMensagem() {
        return codigoTipoMensagem;
    }

    public static MensagemHistoricoType fromValue(final byte codigo){

        MensagemHistoricoType type = MensagemHistoricoType.SUCESSO;

        for(MensagemHistoricoType msgType : MensagemHistoricoType.values()){
            if(msgType.getCodigoTipoMensagem() == codigo){
                type = msgType;
                break;
            }
        }

        return type;

    }

}
