package br.com.api.calculos.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.api.calculos.bo.AnexoBO;
import br.com.api.calculos.converter.AnexoConverter;
import br.com.api.calculos.model.MAnexo;
import br.com.api.calculos.repository.AnexoRepository;

/**
 * Serve o consumidor da API respondendo as requisições da camada 
 * de controllers da entidade anexo
 */
@Service
public class AnexoService {
    
    @Autowired
    private AnexoRepository repository;

    @Autowired
    private AnexoConverter converter;

    public Page<AnexoBO> listar(final Pageable paginacao){

        final Page<AnexoBO> anexos = repository
            .findAll(paginacao)
            .map(converter::toBO);

        return anexos;

    }

    public AnexoBO listarPorId(final Long id){
        
        AnexoBO anexoBO = new AnexoBO();

        Optional<MAnexo> anexCandidato =  repository.findById(id);

        if( anexCandidato.isPresent() ){
            anexoBO = converter.toBO(anexCandidato.get());
        }

        return anexoBO;

    }

    public AnexoBO uploadCsv(final MultipartFile file){
        
        final MAnexo mAnexo = new MAnexo();

        try{
            
            mAnexo.setName(LocalDateTime.now().getNano() + "_" + file.getOriginalFilename());
            mAnexo.setContentType(file.getContentType());
            mAnexo.setData(file.getBytes());
            mAnexo.setCreatedAt(LocalDateTime.now());
            mAnexo.setStatus('A');

            repository.save(mAnexo);

            return converter.toBO(mAnexo);

        }catch(final Exception exception){
            return new AnexoBO();
        }

    }

    public ResponseEntity<ByteArrayResource> downloadCsv(final Long id){

        final Optional<MAnexo> anexoCandidato = repository.findById(id);

        if( !anexoCandidato.isPresent() ){
            byte[] emptyBytes = new byte[0];
            return ResponseEntity.ok()
                .body( new ByteArrayResource(emptyBytes) );
        }

        final MAnexo mAnexo = anexoCandidato.get();

        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + mAnexo.getName());
        headers.add(HttpHeaders.CONTENT_TYPE, mAnexo.getContentType());
        headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf( mAnexo.getData().length) );

        return ResponseEntity.ok()
            .headers(headers)
            .body(new ByteArrayResource(mAnexo.getData()));

    }

}
