package br.com.api.calculos.converter;

import org.springframework.stereotype.Component;

import br.com.api.calculos.model.MAnexo;
import br.com.api.calculos.vo.AnexoVO;

/**
 * Converte VO para Model e Model para VO
 * em representações de objetos da entidade anexo
 */
@Component
public class AnexoConverter {
 
    public AnexoVO toVO(final MAnexo origin){

        final AnexoVO dest = new AnexoVO();

        dest.setId( origin.getId() );
        dest.setName( origin.getName() );
        dest.setContentType( origin.getContentType() );
        dest.setCreatedAt( origin.getCreatedAt() );
        dest.setStatus( origin.getStatus() );
        dest.setData( origin.getData() );

        return dest;

    }

    public MAnexo toModel(final AnexoVO origin){

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
