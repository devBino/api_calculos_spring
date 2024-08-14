package br.com.api.calculos.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import br.com.api.calculos.domain.converter.AnexoHistoricoConverter;
import br.com.api.calculos.domain.model.MAnexo;
import br.com.api.calculos.domain.repository.AnexoHistRepository;
import br.com.api.calculos.domain.repository.AnexoRepository;
import br.com.api.calculos.domain.response.AnexoHistoricoResponse;
import br.com.api.calculos.domain.vo.GenericParamIDVO;
import br.com.api.calculos.domain.vo.PaginateParansVO;
import jakarta.validation.ConstraintViolation;

/**
 * Serve o consumidor da API respondendo as requisições da camada 
 * de controllers da entidade anexo historico.
 * 
 * Na API essa entidade é apenas lida, a inserção e updates 
 * serão realizadas por outro processo, durante processamento
 * dos anexos enviados via API.
 */
@Service
public class AnexoHistoricoService {
    
    @Autowired
    private AnexoRepository anexoRepository;

    @Autowired
    private AnexoHistRepository anexoHistRepository;
    
    @Autowired
    private LocalValidatorFactoryBean validator;

    @Autowired
    private AnexoHistoricoResponse anexoHistoricoResponse;

    @Autowired
    private AnexoHistoricoConverter converter;

    public ResponseEntity<?> listar(final PaginateParansVO pagVO){
    	
    	Set<ConstraintViolation<PaginateParansVO>> erros = validator.validate(pagVO);

        if( !erros.isEmpty() ){
            return anexoHistoricoResponse.buildResponseErrosPaginacao(erros);
        }

        Integer vPage = Integer.valueOf(pagVO.getPage());

        final Pageable paginacao = PageRequest.of(
        		--vPage, Integer.valueOf( pagVO.getLimite() ));
        
        return ResponseEntity.ok(
        		anexoHistRepository
		            .findAll(paginacao)
		            .map(converter::toVo) );
        
    }

    public ResponseEntity<?> listarPorAnexoId(final String id){

    	GenericParamIDVO idVO = new GenericParamIDVO(id);

        Set<ConstraintViolation<GenericParamIDVO>> erros = validator.validate(idVO);

        if( !erros.isEmpty() ){
            return anexoHistoricoResponse.buildResponseErrosParamId(erros);
        }
        
        final Optional<MAnexo> anexoCandidato = anexoRepository.findById(Long.valueOf(id));

        if(!anexoCandidato.isPresent()){
        	return ResponseEntity.ok( List.of() );
        }

        final MAnexo mAnexo = anexoCandidato.get();

        return ResponseEntity.ok(
        		mAnexo.getHistoricos()
		            .stream()
		            .map(converter::toVo)
		            .collect(Collectors.toList()) );

    }

}
