package br.com.api.calculos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.calculos.bo.AnexoHistoricoBO;
import br.com.api.calculos.service.AnexoHistoricoService;

/**
 * Camada de controller da entidade anexo historico, recebe as requisições 
 * e envia para camada de service de anexo historico.
 * 
 * Na API essa entidade é apenas lida, a inserção e updates 
 * serão realizadas por outro processo, durante processamento
 * dos anexos enviados via API.
 */
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:8000","http://localhost:8080","http://localhost:8090"})
@RestController
@RequestMapping("/anexo-historico")
public class AnexoHistoricoController {
    
    @Autowired
    private AnexoHistoricoService service;

    @GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<AnexoHistoricoBO>> listar(
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "limit", defaultValue = "10") Integer limit
    ){

        final Pageable paginacao = PageRequest.of(--page, limit);
        return ResponseEntity.ok(service.listar(paginacao));

    }

    @GetMapping(
        value = "/{idAnexo}", 
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<AnexoHistoricoBO> listarPorAnexoId(@PathVariable(value = "idAnexo") String idAnexo){
        return service.listarPorAnexoId(Long.valueOf(idAnexo));
    }

}
