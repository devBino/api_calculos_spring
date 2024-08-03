package br.com.api.calculos.domain.converter;

import org.springframework.stereotype.Component;

import br.com.api.calculos.domain.model.MCalculoHistorico;
import br.com.api.calculos.domain.vo.CalculoHistoricoVO;

/**
 * Converte VO para Model e Model para VO
 * em representações de objetos da entidade calculo historico
 */
@Component
public class CalculoHistoricoConverter {
    
    public CalculoHistoricoVO toVo(final MCalculoHistorico origin){

        final CalculoHistoricoVO dest = new CalculoHistoricoVO();
        
        dest.setId( origin.getId() );
        dest.setCalculoId( origin.getCalculo().getId() );
        dest.setDescricao( origin.getDescricao() );
        dest.setTipo( origin.getTipo() );

        return dest;

    }

}
