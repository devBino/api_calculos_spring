package br.com.api.calculos.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.calculos.vo.ApiInfoVO;

/**
 * Controller para responder requisições sobre
 * informações a respeito da api
 */
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:8000","http://localhost:8080","http://localhost:8090"})
@RestController
@RequestMapping("/api-info")
public class ApiInfoController {
    
    @GetMapping(
        value = "/sobre", 
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
        }
    )
    public ApiInfoVO sobre(){
        return new ApiInfoVO();
    }

}
