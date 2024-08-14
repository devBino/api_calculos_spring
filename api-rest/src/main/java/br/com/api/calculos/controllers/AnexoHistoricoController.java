package br.com.api.calculos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.calculos.domain.service.AnexoHistoricoService;
import br.com.api.calculos.domain.vo.AnexoHistoricoVO;
import br.com.api.calculos.domain.vo.PaginateParansVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Camada de controller da entidade anexo historico, recebe as requisições 
 * e envia para camada de service de anexo historico.
 * 
 * Na API essa entidade é apenas lida, a inserção e updates 
 * serão realizadas por outro processo, durante processamento
 * dos anexos enviados via API.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/anexo-historico")
@Tag(name = "AnexoHistorico", description = "Endpoints para Gerenciar Historicos de Anexos")
public class AnexoHistoricoController {
  
    @Autowired
    private AnexoHistoricoService service;

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
        summary = "Listagem paginada dos Históricos dos Anexos",
        description = "Listagem paginada dos Históricos dos Anexos",
        tags = {"AnexoHistorico"},
        responses = {
            @ApiResponse(
                description = "Success", responseCode = "200",
                content = {
                    @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        array = @ArraySchema(schema = @Schema(implementation = AnexoHistoricoVO.class))
                    ),
                    @Content(
                        mediaType = MediaType.APPLICATION_XML_VALUE,
                        array = @ArraySchema(schema = @Schema(implementation = AnexoHistoricoVO.class))
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
        @RequestParam(value = "limit") String limit
    ){
    	
    	PaginateParansVO pagVO = new PaginateParansVO(page, limit);
        return service.listar(pagVO);
        
    }

    /**
     * Recebe requisição GET e retorna os historicos de um anexo pelo id do anexo
     * @param idAnexo
     * @return
     */
    @GetMapping(
        value = "/{idAnexo}", 
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
        }
    )
    @Operation(
        summary = "Listagem de Históricos de Anexos pelo anexo id",
        description = "Listagem de Históricos de Anexos pelo anexo id",
        tags = {"AnexoHistorico"},
        responses = {
            @ApiResponse(
                description = "Success", responseCode = "200",
                content = {
                    @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        array = @ArraySchema(schema = @Schema(implementation = AnexoHistoricoVO.class))
                    ),
                    @Content(
                        mediaType = MediaType.APPLICATION_XML_VALUE,
                        array = @ArraySchema(schema = @Schema(implementation = AnexoHistoricoVO.class))
                    )
                }
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<?> listarPorAnexoId(@PathVariable(value = "idAnexo") String idAnexo){
        return service.listarPorAnexoId(idAnexo);
    }

}
