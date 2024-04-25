package br.com.api.calculos.service;

import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.calculos.bo.TokenBO;
import br.com.api.calculos.bo.UsuarioBO;
import br.com.api.calculos.converter.UsuarioConverter;
import br.com.api.calculos.model.MUsuario;
import br.com.api.calculos.repository.UsuarioRepository;

/**
 * Serve o consumidor da API respondendo as requisições da camada 
 * de controllers da entidade usuario
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    
    public TokenBO generateToken(final String user, final String password){

        String encodePass = new String(Base64.getEncoder().encode(password.getBytes()));

        MUsuario mUsuario = repository.findUserByCredentials(user, encodePass);

        return new TokenBO();

    }

    public UsuarioBO detalhar(final String user, final String password){
        
        String encodePass = new String(Base64.getEncoder().encode(password.getBytes()));

        MUsuario mUsuario = repository.findUserByCredentials(user, encodePass);

        if( mUsuario == null ){
            return new UsuarioBO();
        }

        return UsuarioConverter.toBo(mUsuario);

    }

}
