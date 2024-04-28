package br.com.api.calculos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.calculos.bo.CalculoHistoricoBO;
import br.com.api.calculos.service.CalculoHistoricoService;

/**
 * Camada de controller da entidade calculo hisotirico, recebe as requisições 
 * e envia para camada de service de calculo historico.
 * 
 * Na API essa entidade é apenas lida, a inserção e updates 
 * serão realizadas por outro processo, durante processamento dos calculos.
 */
@RestController
@RequestMapping("/calculo-historico")
public class CalculoHistoricoController {
    
    @Autowired
    private CalculoHistoricoService service;

    @GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<CalculoHistoricoBO>> listar(
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "limit", defaultValue = "10") Integer limit
    ){
        
        final Pageable paginacao = PageRequest.of(--page, limit);
        return ResponseEntity.ok(service.listar(paginacao));

    }

    @GetMapping(
        value = "/{idCalculo}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<CalculoHistoricoBO> listarPorCalculoId(@PathVariable(value = "idCalculo") String idCalculo){
        return service.listarPorCalculoId(Long.valueOf(idCalculo));
    }

}
