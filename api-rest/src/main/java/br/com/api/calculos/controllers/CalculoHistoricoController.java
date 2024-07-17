package br.com.api.calculos.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.calculos.domain.response.CalculoHistoricoResponse;
import br.com.api.calculos.domain.service.CalculoHistoricoService;
import br.com.api.calculos.domain.vo.GenericParamIDVO;
import br.com.api.calculos.domain.vo.PaginateParansVO;
import jakarta.validation.ConstraintViolation;

/**
 * Camada de controller da entidade calculo historico, recebe as requisições 
 * e envia para camada de service de calculo historico.
 * 
 * Na API essa entidade é apenas lida, a inserção e updates 
 * serão realizadas por outro processo, durante processamento dos calculos.
 */
@CrossOrigin
@RestController
@RequestMapping("/calculo-historico")
public class CalculoHistoricoController {
    
    @Autowired
    private LocalValidatorFactoryBean validator;

    @Autowired
    private CalculoHistoricoResponse calculoHistoricoResponse;

    @Autowired
    private CalculoHistoricoService service;

    /**
     * Recebe requisição GET para para listagem paginada
     * dos registros
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(
        value = "/listar", 
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
        }
    )
    public ResponseEntity<?> listar(
        @RequestParam(value = "page") String page,
        @RequestParam(value = "limit") String limit
    ){
        
        PaginateParansVO pagVO = new PaginateParansVO(page, limit);

        Set<ConstraintViolation<PaginateParansVO>> erros = validator.validate(pagVO);

        if( !erros.isEmpty() ){
            return calculoHistoricoResponse.buildResponseErrosPaginacao(erros);
        }

        Integer vPage = Integer.valueOf(page);

        final Pageable paginacao = PageRequest.of(--vPage, Integer.valueOf(limit));
        return ResponseEntity.ok(service.listar(paginacao));

    }

    /**
     * Recebe requisição GET e retorna os historicos de um anexo pelo id do anexo
     * @param idAnexo
     * @return
     */
    @GetMapping(
        value = "/{idCalculo}",
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
        }
    )
    public ResponseEntity<?> listarPorCalculoId(@PathVariable(value = "idCalculo") String idCalculo){
        
        GenericParamIDVO idVO = new GenericParamIDVO(idCalculo);

        Set<ConstraintViolation<GenericParamIDVO>> erros = validator.validate(idVO);

        if( !erros.isEmpty() ){
            return calculoHistoricoResponse.buildResponseErrosParamId(erros);
        }

        return ResponseEntity.ok( service.listarPorCalculoId(Long.valueOf(idCalculo)) );

    }

}
