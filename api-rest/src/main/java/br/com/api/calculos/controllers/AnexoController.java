package br.com.api.calculos.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.api.calculos.domain.response.AnexoResponse;
import br.com.api.calculos.domain.service.AnexoService;
import br.com.api.calculos.domain.vo.GenericParamIDVO;
import br.com.api.calculos.domain.vo.PaginateParansVO;
import jakarta.validation.ConstraintViolation;

/**
 * Camada de controller da entidade usuario, recebe as requisições 
 * e envia para camada de service do anexo
 */
@CrossOrigin
@RestController
@RequestMapping("/anexos")
public class AnexoController {
    
    @Autowired
    private LocalValidatorFactoryBean validator;

    @Autowired
    private AnexoResponse anexoResponse;

    @Autowired
    private AnexoService service;

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
            return anexoResponse.buildResponseErrosPaginacao(erros);
        }

        Integer vPage = Integer.valueOf(page);

        final Pageable paginacao = PageRequest.of(--vPage, Integer.valueOf(limit));
        return ResponseEntity.ok(service.listar(paginacao));

    }

    /**
     * Recebe requisição GET para detalhar um anexo
     * @param id
     * @return
     */
    @GetMapping(
        value = "/{id}", 
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
        }
    )
    public ResponseEntity<?> listarPorId(@PathVariable(value = "id") String id){

        GenericParamIDVO idVO = new GenericParamIDVO(id);

        Set<ConstraintViolation<GenericParamIDVO>> erros = validator.validate(idVO);

        if( !erros.isEmpty() ){
            return anexoResponse.buildResponseErrosParamId(erros);
        }

        return ResponseEntity.ok( service.listarPorId(Long.valueOf(id)) );

    }

    /**
     * Recebe requisição POST para upload do arquivo csv
     * contendo os calculos a serem processados
     * @param file
     * @return
     */
    @PostMapping(
        value = "/upload/csv",
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
        }
    )
    public ResponseEntity<?> uploadCsv(@RequestParam(value = "file") MultipartFile file){
        
        try{

            if( !Objects.isNull(file.getContentType())
                && !file.getContentType().equals("text/csv")  ){
                return anexoResponse.buildResponseErros(Map.of("conteudoArquivo", "Era esperado um arquivo text/csv"));
            }

            final String conteudoArquivo = new String( file.getBytes() );

            int totalLinhas = conteudoArquivo.split("\n").length;

            //valida total de 50 linhas mais a linha de cabeçalho
            if(conteudoArquivo.isEmpty() || totalLinhas > 51){
                return anexoResponse.buildResponseErros(Map.of("conteudoArquivo", "O arquivo deve ter no mínimo 1 linha e no máximo 50 linhas além da linha de cabeçalho"));
            }

            return ResponseEntity.ok( service.uploadCsv(file) );

        }catch(final Exception exception){
            return anexoResponse.buildResponseErros(Map.of("conteudoArquivo", "Por favor, revise o conteúdo do arquivo"));
        }

    }

    /**
     * Recebe requisição GET para baixar o conteúdo do arquivo csv
     */
    @GetMapping(value = "/download/csv/{id}")
    public ResponseEntity<?> downloadCsv(@PathVariable(value = "id") String id){

        GenericParamIDVO idVO = new GenericParamIDVO(id);

        Set<ConstraintViolation<GenericParamIDVO>> erros = validator.validate(idVO);

        if( !erros.isEmpty() ){
            return anexoResponse.buildResponseErrosParamId(erros);
        }

        return service.downloadCsv(Long.valueOf(id));

    }

}
