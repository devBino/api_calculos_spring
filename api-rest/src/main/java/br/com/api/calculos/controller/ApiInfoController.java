package br.com.api.calculos.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.api.calculos.bo.ApiInfoBO;

/**
 * Controller para responder requisições sobre
 * informações a respeito da api
 */
@RestController
@RequestMapping("/api-info")
public class ApiInfoController {
    
    @GetMapping(value = "/sobre", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiInfoBO sobre(){
        return new ApiInfoBO();
    }

}
