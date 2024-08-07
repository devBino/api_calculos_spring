package br.com.api.calculos.domain.converter;

import org.springframework.stereotype.Component;

import br.com.api.calculos.domain.model.MAnexoHistorico;
import br.com.api.calculos.domain.vo.AnexoHistoricoVO;

/**
 * Converte VO para Model e Model para VO
 * em representações de objetos da entidade anexo historico
 */
@Component
public class AnexoHistoricoConverter {
    
    public AnexoHistoricoVO toVo(final MAnexoHistorico origin){
        
        final AnexoHistoricoVO dest = new AnexoHistoricoVO();

        dest.setId( origin.getId() );
        dest.setAnexoId( origin.getAnexo().getId() );
        dest.setDescricao( origin.getDescricao() );
        dest.setTipo( origin.getTipo() );

        return dest;

    }

}
