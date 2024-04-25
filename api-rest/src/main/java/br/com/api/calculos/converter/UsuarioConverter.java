package br.com.api.calculos.converter;

import org.springframework.stereotype.Component;

import br.com.api.calculos.bo.UsuarioBO;
import br.com.api.calculos.model.MUsuario;

/**
 * Converte BO para Model e Model para BO
 * em representações de objetos da entidade usuario
 */
@Component
public class UsuarioConverter {
    
    public UsuarioBO toBo(final MUsuario origin){

        final UsuarioBO dest = new UsuarioBO();

        dest.setId( origin.getId() );
        dest.setNome( origin.getNome() );
        dest.setUser( origin.getUser() );
        dest.setPassword( origin.getPassword() );
        dest.setAtivo( origin.getAtivo() );

        return dest;

    }

    public MUsuario toModel(final UsuarioBO origin){

        final MUsuario dest = new MUsuario();

        dest.setId( origin.getId() );
        dest.setNome( origin.getNome() );
        dest.setUser( origin.getUser() );
        dest.setPassword( origin.getPassword() );
        dest.setAtivo( origin.getAtivo() );

        return dest;

    }

}
