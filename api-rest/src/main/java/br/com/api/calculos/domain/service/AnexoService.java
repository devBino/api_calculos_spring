package br.com.api.calculos.domain.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.MultipartFile;

import br.com.api.calculos.domain.converter.AnexoConverter;
import br.com.api.calculos.domain.model.MAnexo;
import br.com.api.calculos.domain.repository.AnexoRepository;
import br.com.api.calculos.domain.response.AnexoResponse;
import br.com.api.calculos.domain.vo.AnexoVO;
import br.com.api.calculos.domain.vo.GenericParamIDVO;
import br.com.api.calculos.domain.vo.ListaAnexosVO;
import br.com.api.calculos.domain.vo.PaginateParansVO;
import jakarta.validation.ConstraintViolation;

/**
 * Serve o consumidor da API respondendo as requisições da camada 
 * de controllers da entidade anexo
 */
@Service
public class AnexoService {
    
    @Autowired
    private AnexoRepository repository;
    
    @Autowired
    private LocalValidatorFactoryBean validator;

    @Autowired
    private AnexoResponse anexoResponse;

    @Autowired
    private AnexoConverter converter;

    public ResponseEntity<?> listar(final PaginateParansVO pagVO){

        Set<ConstraintViolation<PaginateParansVO>> erros = validator.validate(pagVO);

        if( !erros.isEmpty() ){
            return anexoResponse.buildResponseErrosPaginacao(erros);
        }

        Integer vPage = Integer.valueOf(pagVO.getPage());

        final Pageable paginacao = PageRequest.of(
        		--vPage, Integer.valueOf( pagVO.getLimite() ));
        
        final Page<AnexoVO> anexos = repository
            .findAll(paginacao)
            .map(converter::toVO);

        ListaAnexosVO lista = new ListaAnexosVO();

        lista.setAnexos(anexos.getContent());
        lista.setTotalPaginas(anexos.getTotalPages());
        lista.setTotalRegistros(anexos.getTotalElements());

        return ResponseEntity.ok( lista );

    }

    public ResponseEntity<?> detalhar(final String id){
    	
    	GenericParamIDVO idVO = new GenericParamIDVO(id);

        Set<ConstraintViolation<GenericParamIDVO>> erros = validator.validate(idVO);

        if( !erros.isEmpty() ){
            return anexoResponse.buildResponseErrosParamId(erros);
        }

        AnexoVO anexoVO = new AnexoVO();

        Optional<MAnexo> anexCandidato =  repository.findById(Long.valueOf(id));

        if( anexCandidato.isPresent() ){
            anexoVO = converter.toVO(anexCandidato.get());
        }

        return ResponseEntity.ok( anexoVO );

    }

    public ResponseEntity<?> uploadCsv(final MultipartFile file)
    	throws Exception {
        
        final MAnexo mAnexo = new MAnexo();

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
        	
            mAnexo.setName(LocalDateTime.now().getNano() + "_" + file.getOriginalFilename());
            mAnexo.setContentType(file.getContentType());
            mAnexo.setData(file.getBytes());
            mAnexo.setCreatedAt(LocalDateTime.now());
            mAnexo.setStatus('A');

            repository.save(mAnexo);

            return ResponseEntity.ok( converter.toVO(mAnexo) );

        }catch(final Exception exception){
        	throw exception;
        }

    }

    public ResponseEntity<?> downloadCsv(final String id){

    	GenericParamIDVO idVO = new GenericParamIDVO(id);

        Set<ConstraintViolation<GenericParamIDVO>> erros = validator.validate(idVO);

        if( !erros.isEmpty() ){
            return anexoResponse.buildResponseErrosParamId(erros);
        }
        
        final Optional<MAnexo> anexoCandidato = repository.findById( Long.valueOf(id) );

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
