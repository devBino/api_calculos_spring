package br.com.api.calculos.converter;

import org.springframework.stereotype.Component;

import br.com.api.calculos.bo.CalculoHistoricoBO;
import br.com.api.calculos.model.MCalculoHistorico;

/**
 * Converte BO para Model e Model para BO
 * em representações de objetos da entidade calculo historico
 */
@Component
public class CalculoHistoricoConverter {
    
    public CalculoHistoricoBO toBo(final MCalculoHistorico origin){

        final CalculoHistoricoBO dest = new CalculoHistoricoBO();
        
        dest.setId( origin.getId() );
        dest.setCalculoId( origin.getCalculo().getId() );
        dest.setDescricao( origin.getDescricao() );
        dest.setTipo( origin.getTipo() );

        return dest;

    }

}
