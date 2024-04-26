package br.com.api.calculos.converter;

import org.springframework.stereotype.Component;

import br.com.api.calculos.bo.CalculoBO;
import br.com.api.calculos.model.MCalculo;

/**
 * Converte BO para Model e Model para BO
 * em representações de objetos da entidade calculo
 */
@Component
public class CalculoConverter {
 
    public CalculoBO toBo(final MCalculo origin){

        final CalculoBO dest = new CalculoBO();

        dest.setId( origin.getId() );
        dest.setDescricao( origin.getDescricao() );
        dest.setNumero1( origin.getNumero1() );
        dest.setNumero2( origin.getNumero2() );
        dest.setResultado( origin.getResultado() );
        dest.setSinal( origin.getSinal() );
        dest.setEstado( origin.getEstado() );

        return dest;

    }

    public MCalculo toModel(final CalculoBO origin){

        final MCalculo dest = new MCalculo();

        dest.setId( origin.getId() );
        dest.setDescricao( origin.getDescricao() );
        dest.setNumero1( origin.getNumero1() );
        dest.setNumero2( origin.getNumero2() );
        dest.setResultado( origin.getResultado() );
        dest.setSinal( origin.getSinal() );
        dest.setEstado( origin.getEstado() );

        return dest;

    }
    
}
