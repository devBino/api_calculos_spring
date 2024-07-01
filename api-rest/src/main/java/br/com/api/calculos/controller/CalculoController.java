package br.com.api.calculos.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.calculos.response.CalculoResponse;
import br.com.api.calculos.service.CalculoService;
import br.com.api.calculos.vo.CalculoVO;
import br.com.api.calculos.vo.GenericParamIDVO;
import br.com.api.calculos.vo.PaginateParansVO;
import jakarta.validation.ConstraintViolation;

import org.springframework.web.bind.annotation.PutMapping;

/**
 * Camada de controller da entidade calculo, recebe as requisições 
 * e envia para camada de service de calculo
 */
@RestController
@RequestMapping("/calculos")
public class CalculoController {
    
    @Autowired
    private LocalValidatorFactoryBean validator;

    @Autowired
    private CalculoResponse calculoResponse;
    
    @Autowired
    private CalculoService service;

    /**
     * Recebe requisição POST para criar um calculo
     * @param body
     * @return
     */
    @PostMapping(
        value = "/criar", 
        consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
        },
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
        }
    )
    public ResponseEntity<?> criar(@RequestBody CalculoVO body){
        
        Set<ConstraintViolation<CalculoVO>> erros = validator.validate(body);

        if( !erros.isEmpty() ){
            return calculoResponse.buildResponseErros(erros);
        }

        return ResponseEntity.ok( service.criar(body) );

    }

    /**
     * Recebe uma requisição POST para enviar
     * os parametro de um calculo para a Queue SQS na AWS 
     * @param body
     * @return
     */
    @PostMapping(
        value = "/criar-aws",
        consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
        },
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
        }
    )
    public ResponseEntity<?> criarCalculoFilaAws(@RequestBody CalculoVO body){
        
        Set<ConstraintViolation<CalculoVO>> erros = validator.validate(body);

        if( !erros.isEmpty() ){
            return calculoResponse.buildResponseErros(erros);
        }

        return ResponseEntity.ok( service.criarCalculoAws(body) );

    }

    /**
     * Requisição PUT para atualizar um calculo
     * @param body
     * @return
     */
    @PutMapping(
        value = "/atualizar", 
        consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
        },
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
        }
    )
    public ResponseEntity<?> atualizar(@RequestBody CalculoVO body){
        
        if( Objects.isNull(body.getId()) ){
            return calculoResponse.buildResponseErros(Map.of("id", "campo obrigatório"));
        }

        Set<ConstraintViolation<CalculoVO>> erros = validator.validate(body);

        if( !erros.isEmpty() ){
            return calculoResponse.buildResponseErros(erros);
        }

        return ResponseEntity.ok( service.atualizar(body) );
        
    }

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
            return calculoResponse.buildResponseErrosPaginacao(erros);
        }

        Integer vPage = Integer.valueOf(page);
        
        Pageable paginacao = PageRequest.of(--vPage, Integer.valueOf(limit));
        return ResponseEntity.ok(service.listar(paginacao));

    }

    /**
     * Recebe requisição GET para listar calculos pelo sinal
     * @param sinal
     * @return
     */
    @GetMapping(
        value = "/listar-por-sinal/{sinal}",
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
        }
    )
    public List<CalculoVO> listarPorSinal(@PathVariable(value = "sinal") Byte sinal){
        return service.listarPorSinal(sinal);
    }

    /**
     * Recebe requisição GET para detalhar um calculo
     * @param id
     * @return
     */
    @GetMapping(
        value = "/detalhar/{id}", 
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
        }
    )
    public ResponseEntity<?> detalhar(@PathVariable(value = "id") String id){
        
        GenericParamIDVO idVO = new GenericParamIDVO(id);

        Set<ConstraintViolation<GenericParamIDVO>> erros = validator.validate(idVO);

        if( !erros.isEmpty() ){
            return calculoResponse.buildResponseErrosParamId(erros);
        }
        
        return ResponseEntity.ok( service.detalhar(Long.valueOf(id)) );
        
    }

    /**
     * Recebe requisição GET para detalhar calculo enviado para
     * Queue SQS na AWS
     * @param calculoUU
     * @return
     */
    @GetMapping(
        value = "/detalhar-calculo-aws/{calculoUU}", 
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
        }
    )
    public ResponseEntity<?> detalharCalculoAWS(@PathVariable(value = "calculoUU") String calculoUU){

        if(Objects.isNull(calculoUU) || calculoUU.isEmpty() || calculoUU.isBlank()){
            return calculoResponse.buildResponseErros(Map.of("CalculoUU", "Campo Obrigatório e deve conter valor"));
        }

        return ResponseEntity.ok(service.detalharCalculoAws(calculoUU));

    }

    /**
     * Recebe requisição DELETE para remover o registro
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable(value = "id") String id){
        
        GenericParamIDVO idVO = new GenericParamIDVO(id);

        Set<ConstraintViolation<GenericParamIDVO>> erros = validator.validate(idVO);

        if( !erros.isEmpty() ){
            return calculoResponse.buildResponseErrosParamId(erros);
        }

        service.deletar(Long.valueOf(id));

        return ResponseEntity.noContent().build();

    }

}
