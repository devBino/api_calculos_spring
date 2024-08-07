package br.com.api.calculos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.calculos.domain.service.AuthService;
import br.com.api.calculos.domain.vo.TokenVO;

/**
 * Controller para autenticar usuario e gerar token
 */
@CrossOrigin
@RestController
@RequestMapping("/auth")
public class Auth {
    
    @Autowired
    private AuthService service;

    @GetMapping(
        value = "/token/{user}/{password}",
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
        }
    )
    public TokenVO gerarToken(
        @PathVariable(value = "user") String user,
        @PathVariable(value = "password") String password
    ){
        return service.gerarToken(user, password);
    }

    @GetMapping(value = "/teste/token")
    public ResponseEntity<?> validarToken(){
        return ResponseEntity.noContent().build();
    }

}
