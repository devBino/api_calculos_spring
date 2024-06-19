package br.com.api.calculos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.api.calculos.model.MUsuario;
import br.com.api.calculos.model.ifacejpa.UsuarioRepository;
import br.com.api.calculos.provider.TokenProvider;
import br.com.api.calculos.vo.TokenVO;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Objects;

/**
 * Serviço que responde a camada de controller
 * para autenticar usuário e gerar um token
 */
@Service
public class AuthService {
    
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenProvider tokenProvider;

    public TokenVO gerarToken(final String user, final String password){

        TokenVO tokenVO = new TokenVO();

        tokenVO.setMensagem("Não foi possível obter um token com as credenciais informadas");

        try{

            final String encodePass = Base64.getEncoder().encodeToString(password.getBytes());

            final MUsuario mUsuario = repository.findUserByCredentials(user, encodePass);

            if(Objects.isNull(mUsuario)){
                return tokenVO;
            }

            final UserDetails userDetails = new User(mUsuario.getUser(), mUsuario.getPassword(), 
                new ArrayList<>());

            final String token = tokenProvider.generateToken(userDetails);

            tokenVO.setSucesso(true);
            tokenVO.setMensagem("Token Gerado com sucesso");
            tokenVO.setToken(token);
            
        }catch(final Exception exception){
            //NA
        }

        return tokenVO;

    }

}
