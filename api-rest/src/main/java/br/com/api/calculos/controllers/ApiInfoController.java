package br.com.api.calculos.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.calculos.domain.vo.ApiInfoVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controller para responder requisições sobre
 * informações a respeito da api
 */
@RestController
@RequestMapping("/api/api-info")
@Tag(name = "ApiInfo", description = "Endpoints para Informações da API")
public class ApiInfoController {
    
    @GetMapping(
        value = "/sobre", 
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
        }
    )
    @Operation(
        summary = "Endpoint para informações da API",
        description = "Endpoint para informações da API",
        tags = {"ApiInfo"},
        responses = {
            @ApiResponse(
                description = "Success", responseCode = "200",
                content = @Content
            )
        }
    )
    public ApiInfoVO sobre(){
        return new ApiInfoVO();
    }

}
