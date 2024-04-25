package br.com.api.calculos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@RestController
@RequestMapping("/anexos")
public class AnexoController {
    
    @Autowired
    private AnexoService service;

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
