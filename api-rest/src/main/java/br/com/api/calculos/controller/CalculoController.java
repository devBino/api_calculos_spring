package br.com.api.calculos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.calculos.service.CalculoService;
import br.com.api.calculos.vo.CalculoVO;

import org.springframework.web.bind.annotation.PutMapping;


/**
 * Camada de controller da entidade calculo, recebe as requisições 
 * e envia para camada de service de calculo
 */
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:8000","http://localhost:8080","http://localhost:8090"})
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
    public CalculoVO criar(@RequestBody CalculoVO body){
        return service.criar(body);
    }

    @PostMapping(
        value = "/criar-aws",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CalculoVO criarCalculoFilaAws(@RequestBody CalculoVO body){
        return service.criarCalculoAws(body);
    }

    @PutMapping(
        value = "/atualizar", 
        consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CalculoVO atualizar(@RequestBody CalculoVO body){
        return service.atualizar(body);
    }

    @GetMapping(
        value = "/listar", 
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Page<CalculoVO>> listar(
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "limit", defaultValue = "10") Integer limit
    ){
        
        final Pageable paginacao = PageRequest.of(--page, limit);
        return ResponseEntity.ok(service.listar(paginacao));

    }

    @GetMapping(value = "/listar-por-sinal/{sinal}")
    public List<CalculoVO> listarPorSinal(@PathVariable(value = "sinal") Byte sinal){
        return service.listarPorSinal(sinal);
    }

    @GetMapping(value = "/detalhar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CalculoVO detalhar(@PathVariable(value = "id") String id){
        return service.detalhar(Long.valueOf(id));
    }

    @GetMapping(value = "/detalhar-calculo-aws/{calculoUU}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CalculoVO detalharCalculoAWS(@PathVariable(value = "calculoUU") String calculoUU){
        return service.detalharCalculoAws(calculoUU);
    }

    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable(value = "id") String id){
        service.deletar(Long.valueOf(id));
        return ResponseEntity.noContent().build();
    }

}
