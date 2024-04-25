package br.com.api.calculos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.calculos.bo.TokenBO;
import br.com.api.calculos.bo.UsuarioBO;
import br.com.api.calculos.service.UsuarioService;

/**
 * Camada de controller da entidade usuario, recebe as requisições 
 * e envia para camada de service do usuario
 */
@RestController
@RequestMapping("/usuarios")
public class UserController {
    
    @Autowired
    private UsuarioService service;

    @GetMapping(value = "/autenticar/{user}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TokenBO autenticar(
        @PathVariable(value = "user") String user,
        @PathVariable(value = "password") String password
    ){

        return service.generateToken(user, password);

    }

    @GetMapping(value = "/detalhar/{user}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioBO detalhar(
        @PathVariable(value = "user") String user,
        @PathVariable(value = "password") String password
    ){
        
        return service.detalhar(user, password);

    }

}
