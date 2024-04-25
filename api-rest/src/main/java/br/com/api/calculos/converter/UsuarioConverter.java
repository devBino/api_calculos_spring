package br.com.api.calculos.converter;

import br.com.api.calculos.bo.UsuarioBO;
import br.com.api.calculos.model.MUsuario;

public class UsuarioConverter {
    
    public static UsuarioBO toBo(final MUsuario origin){

        final UsuarioBO dest = new UsuarioBO();

        dest.setId( origin.getId() );
        dest.setNome( origin.getNome() );
        dest.setUser( origin.getUser() );
        dest.setPassword( origin.getPassword() );
        dest.setAtivo( origin.getAtivo() );

        return dest;

    }

    public static MUsuario toModel(final UsuarioBO origin){

        final MUsuario dest = new MUsuario();

        dest.setId( origin.getId() );
        dest.setNome( origin.getNome() );
        dest.setUser( origin.getUser() );
        dest.setPassword( origin.getPassword() );
        dest.setAtivo( origin.getAtivo() );

        return dest;

    }

}
