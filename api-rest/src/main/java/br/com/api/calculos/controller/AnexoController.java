package br.com.api.calculos.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.api.calculos.bo.AnexoBO;
import br.com.api.calculos.service.AnexoService;

/**
 * Camada de controller da entidade usuario, recebe as requisições 
 * e envia para camada de service do anexo
 */
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:8000","http://localhost:8080","http://localhost:8090"})
@RestController
@RequestMapping("/anexos")
public class AnexoController {
    
    @Autowired
    private AnexoService service;

    @GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<AnexoBO>> listar(
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "limit", defaultValue = "10") Integer limit
    ){

        final Pageable paginacao = PageRequest.of(--page, limit);
        return ResponseEntity.ok(service.listar(paginacao));

    }

    @PostMapping(
        value = "/upload/csv",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public AnexoBO uploadCsv(@RequestParam(value = "file") MultipartFile file){
        return service.uploadCsv(file);
    }

    @GetMapping(value = "/download/csv/{id}")
    public ResponseEntity<ByteArrayResource> downloadCsv(@PathVariable(value = "id") Long id){
        return service.downloadCsv(id);
    }

}
