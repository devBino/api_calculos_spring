package br.com.api.calculos.converter;

import org.springframework.stereotype.Component;

import br.com.api.calculos.bo.AnexoHistoricoBO;
import br.com.api.calculos.model.MAnexoHistorico;

/**
 * Converte BO para Model e Model para BO
 * em representações de objetos da entidade anexo historico
 */
@Component
public class AnexoHistoricoConverter {
    
    public AnexoHistoricoBO toBo(final MAnexoHistorico origin){
        
        final AnexoHistoricoBO dest = new AnexoHistoricoBO();

        dest.setId( origin.getId() );
        dest.setAnexoId( origin.getAnexo().getId() );
        dest.setDescricao( origin.getDescricao() );
        dest.setTipo( origin.getTipo() );

        return dest;

    }

}
