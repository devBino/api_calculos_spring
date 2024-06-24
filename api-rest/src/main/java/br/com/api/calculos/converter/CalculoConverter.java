package br.com.api.calculos.converter;

import org.springframework.stereotype.Component;

import br.com.api.calculos.model.MCalculo;
import br.com.api.calculos.vo.CalculoVO;

/**
 * Converte VO para Model e Model para VO
 * em representações de objetos da entidade calculo
 */
@Component
public class CalculoConverter {
 
    public CalculoVO toVo(final MCalculo origin){

        final CalculoVO dest = new CalculoVO();

        dest.setId( origin.getId() );
        dest.setCalculoUU( origin.getCalculoUU() );
        dest.setDescricao( origin.getDescricao() );
        dest.setNumero1( origin.getNumero1() );
        dest.setNumero2( origin.getNumero2() );
        dest.setResultado( origin.getResultado() );
        dest.setSinal( origin.getSinal() );
        dest.setEstado( origin.getEstado() );

        return dest;

    }

    public MCalculo toModel(final CalculoVO origin){

        final MCalculo dest = new MCalculo();

        dest.setId( origin.getId() );
        dest.setCalculoUU( origin.getCalculoUU() );
        dest.setDescricao( origin.getDescricao() );
        dest.setNumero1( origin.getNumero1() );
        dest.setNumero2( origin.getNumero2() );
        dest.setResultado( origin.getResultado() );
        dest.setSinal( origin.getSinal() );
        dest.setEstado( origin.getEstado() );

        return dest;

    }
    
}
