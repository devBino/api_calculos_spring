package br.com.api.calculos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.calculos.bo.CalculoBO;
import br.com.api.calculos.service.CalculoService;
import org.springframework.web.bind.annotation.PutMapping;


/**
 * Camada de controller da entidade calculo, recebe as requisições 
 * e envia para camada de service de calculo
 */
@RestController
@RequestMapping("/calculos")
public class CalculoController {
    
    @Autowired
    private CalculoService service;

    @PostMapping(
        value = "/criar", 
        consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CalculoBO criar(@RequestBody CalculoBO body){
        return service.criar(body);
    }

    @PutMapping(
        value = "/atualizar", 
        consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CalculoBO atualizar(@RequestBody CalculoBO body){
        return service.atualizar(body);
    }

    @GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CalculoBO> listar(){
        return service.listar();
    }

    @GetMapping(value = "/listar-por-sinal/{sinal}")
    public List<CalculoBO> listarPorSinal(@PathVariable(value = "sinal") Byte sinal){
        return service.listarPorSinal(sinal);
    }

    @GetMapping(value = "/detalhar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CalculoBO detalhar(@PathVariable(value = "id") String id){
        return service.detalhar(Long.valueOf(id));
    }

    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable(value = "id") String id){
        service.deletar(Long.valueOf(id));
        return ResponseEntity.noContent().build();
    }

}