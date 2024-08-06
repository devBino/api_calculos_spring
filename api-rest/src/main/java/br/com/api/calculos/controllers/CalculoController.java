package br.com.api.calculos.controllers;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.calculos.domain.response.CalculoResponse;
import br.com.api.calculos.domain.service.CalculoService;
import br.com.api.calculos.domain.vo.CalculoVO;
import br.com.api.calculos.domain.vo.GenericParamIDVO;
import br.com.api.calculos.domain.vo.ListaCalculosVO;
import br.com.api.calculos.domain.vo.PaginateParansVO;
import br.com.api.calculos.type.SinalCalculoType;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolation;

/**
 * Camada de controller da entidade calculo, recebe as requisições 
 * e envia para camada de service de calculo
 */
@CrossOrigin
@RestController
@RequestMapping("/api/calculos")
@Tag(name = "Calculo", description = "Endpoints para Gerenciar Calculos")
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
    @Operation(
        summary = "Cria um Novo Calculo",
        description = "Cria um Novo Calculo",
        tags = {"Calculo"},
        responses = {
            @ApiResponse(
                description = "Success", responseCode = "201",
                content = {
                    @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = CalculoVO.class)
                    ),
                    @Content(
                        mediaType = MediaType.APPLICATION_XML_VALUE,
                        schema = @Schema(implementation = CalculoVO.class)
                    )
                }
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
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
    @Hidden
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
    @Operation(
        summary = "Atualiza um Calculo Existente",
        description = "Atualiza um Calculo Existente",
        tags = {"Calculo"},
        responses = {
            @ApiResponse(
                description = "Success", responseCode = "200",
                content = {
                    @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = CalculoVO.class)
                    ),
                    @Content(
                        mediaType = MediaType.APPLICATION_XML_VALUE,
                        schema = @Schema(implementation = CalculoVO.class)
                    )
                }
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
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
    @Operation(
        summary = "Listagem paginada dos Calculos por sinal",
        description = "Listagem paginada dos Calculos por sinal",
        tags = {"Calculo"},
        responses = {
            @ApiResponse(
                description = "Success", responseCode = "200",
                content = {
                    @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ListaCalculosVO.class)
                    ),
                    @Content(
                        mediaType = MediaType.APPLICATION_XML_VALUE,
                        schema = @Schema(implementation = ListaCalculosVO.class)
                    )
                }
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<?> listar(
        @RequestParam(value = "page") String page,
        @RequestParam(value = "limit") String limit,
        @RequestParam(value = "sinal", defaultValue = "na") String sinal
    ){
        
        PaginateParansVO pagVO = new PaginateParansVO(page, limit);

        Set<ConstraintViolation<PaginateParansVO>> erros = validator.validate(pagVO);

        if( !erros.isEmpty() ){
            return calculoResponse.buildResponseErrosPaginacao(erros);
        }

        Integer vPage = Integer.valueOf(page);
        
        Pageable paginacao = PageRequest.of(--vPage, Integer.valueOf(limit));

        if( sinal.equals("na") ){
            return ResponseEntity.ok(service.listar(paginacao));
        }

        SinalCalculoType sinalCalcType = SinalCalculoType.fromCodigo(sinal);

        if(Objects.isNull(sinalCalcType)){
            return calculoResponse.buildResponseErros(Map.of("Sinal", "Sinal inválido enviado, envie os sinais [adi, sub, mul, div]"));
        }

        return ResponseEntity.ok( service.listarPorSinal(sinalCalcType.getSinal(), paginacao) );

    }

    /**
     * Recebe requisição GET para para listagem paginada
     * dos registros por id do anexo
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(
        value = "/listar-por-anexo", 
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
        }
    )
    @Operation(
        summary = "Listagem paginada dos Calculos por anexo id",
        description = "Listagem paginada dos Calculos por anexo id",
        tags = {"Calculo"},
        responses = {
            @ApiResponse(
                description = "Success", responseCode = "200",
                content = {
                    @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ListaCalculosVO.class)
                    ),
                    @Content(
                        mediaType = MediaType.APPLICATION_XML_VALUE,
                        schema = @Schema(implementation = ListaCalculosVO.class)
                    )
                }
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<?> listarPorAnexo(
        @RequestParam(value = "page") String page,
        @RequestParam(value = "limit") String limit,
        @RequestParam(value = "anexoId") String anexoId
    ){
        
        PaginateParansVO pagVO = new PaginateParansVO(page, limit);

        Set<ConstraintViolation<PaginateParansVO>> erros = validator.validate(pagVO);

        if( !erros.isEmpty() ){
            return calculoResponse.buildResponseErrosPaginacao(erros);
        }

        GenericParamIDVO idVO = new GenericParamIDVO(anexoId);

        Set<ConstraintViolation<GenericParamIDVO>> errosAnexoId = validator.validate(idVO);

        if( !errosAnexoId.isEmpty() ){
            return calculoResponse.buildResponseErrosParamId(errosAnexoId);
        }

        Integer vPage = Integer.valueOf(page);
        
        Pageable paginacao = PageRequest.of(--vPage, Integer.valueOf(limit));

        return ResponseEntity.ok( service.listarPorAnexo(Long.valueOf(anexoId), paginacao) );

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
    @Operation(
        summary = "Detalhar Calculo pelo ID",
        description = "Detalhar Calculo pelo ID",
        tags = {"Calculo"},
        responses = {
            @ApiResponse(
                description = "Success", responseCode = "200",
                content = {
                    @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = CalculoVO.class)
                    ),
                    @Content(
                        mediaType = MediaType.APPLICATION_XML_VALUE,
                        schema = @Schema(implementation = CalculoVO.class)
                    )
                }
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
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
    @Hidden
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
    @Operation(
        summary = "Deleta um Calculo pelo seu ID",
        description = "Deleta um Calculo pelo seu ID",
        tags = {"Calculo"},
        responses = {
            @ApiResponse(
                description = "No Content", responseCode = "204",
                content = @Content(
                    mediaType = "application/json"
                )
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
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
