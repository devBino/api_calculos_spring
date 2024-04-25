package br.com.api.calculos.converter;

import org.springframework.stereotype.Component;

import br.com.api.calculos.bo.AnexoBO;
import br.com.api.calculos.model.MAnexo;

/**
 * Converte BO para Model e Model para BO
 * em representações de objetos da entidade anexo
 */
@Component
public class AnexoConverter {
 
    public AnexoBO toBO(final MAnexo origin){

        final AnexoBO dest = new AnexoBO();

        dest.setId( origin.getId() );
        dest.setName( origin.getName() );
        dest.setContentType( origin.getContentType() );
        dest.setCreatedAt( origin.getCreatedAt() );
        dest.setStatus( origin.getStatus() );
        dest.setData( origin.getData() );

        return dest;

    }

    public MAnexo toModel(final AnexoBO origin){

        final MAnexo dest = new MAnexo();

        dest.setId( origin.getId() );
        dest.setName( origin.getName() );
        dest.setContentType( origin.getContentType() );
        dest.setCreatedAt( origin.getCreatedAt() );
        dest.setStatus( origin.getStatus() );
        dest.setData( origin.getData() );

        return dest;

    }
    
}
