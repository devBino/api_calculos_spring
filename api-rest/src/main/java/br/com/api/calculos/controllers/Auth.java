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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controller para autenticar usuario e gerar token
 */
@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "Endpoints para Gerenciar Authenticação")
public class Auth {
    
    @Autowired
    private AuthService service;

    /**
     * Recebe requisição GET para geração de token
     * @param user
     * @param password
     * @return
     */
    @GetMapping(
        value = "/token/{user}/{password}",
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
        }
    )
    @Operation(
        summary = "Autenticação e Geração de Token",
        description = "Autenticação e Geração de Token",
        tags = {"Auth"},
        responses = {
            @ApiResponse(
                description = "Success", responseCode = "200",
                content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = TokenVO.class)
                )
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public TokenVO gerarToken(
        @PathVariable(value = "user") String user,
        @PathVariable(value = "password") String password
    ){
        return service.gerarToken(user, password);
    }

    /**
     * Testa requisição GET não autorizada, para testar token
     * @return
     */
    @GetMapping(value = "/teste/token")
    @Operation(
        summary = "Endpoint para testar token",
        description = "Endpoint para testar token",
        tags = {"Auth"},
        responses = {
            @ApiResponse(
                description = "Success", responseCode = "200",
                content = @Content
            ),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content)
        }
    )
    public ResponseEntity<?> validarToken(){
        return ResponseEntity.noContent().build();
    }

}
