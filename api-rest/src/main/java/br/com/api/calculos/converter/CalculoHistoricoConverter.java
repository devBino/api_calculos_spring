package br.com.api.calculos.converter;

import org.springframework.stereotype.Component;

import br.com.api.calculos.bo.CalculoHistoricoBO;
import br.com.api.calculos.model.MCalculoHistorico;

/**
 * Converte entidade CalculoHistorico para CalculoHistoricoBO
 * para leitura na API
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
