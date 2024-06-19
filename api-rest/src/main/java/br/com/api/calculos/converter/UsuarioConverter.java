package br.com.api.calculos.converter;

import org.springframework.stereotype.Component;

import br.com.api.calculos.model.MUsuario;
import br.com.api.calculos.vo.UsuarioVO;

/**
 * Converte VO para Model e Model para VO
 * em representações de objetos da entidade usuario
 */
@Component
public class UsuarioConverter {
    
    public UsuarioVO toBo(final MUsuario origin){

        final UsuarioVO dest = new UsuarioVO();

        dest.setId( origin.getId() );
        dest.setNome( origin.getNome() );
        dest.setUser( origin.getUser() );
        dest.setPassword( origin.getPassword() );
        dest.setAtivo( origin.getAtivo() );

        return dest;

    }

    public MUsuario toModel(final UsuarioVO origin){

        final MUsuario dest = new MUsuario();

        dest.setId( origin.getId() );
        dest.setNome( origin.getNome() );
        dest.setUser( origin.getUser() );
        dest.setPassword( origin.getPassword() );
        dest.setAtivo( origin.getAtivo() );

        return dest;

    }

}
