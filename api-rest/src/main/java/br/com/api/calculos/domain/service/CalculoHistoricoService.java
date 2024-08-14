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

import br.com.api.calculos.domain.converter.CalculoHistoricoConverter;
import br.com.api.calculos.domain.model.MCalculo;
import br.com.api.calculos.domain.repository.CalculoHistReporitory;
import br.com.api.calculos.domain.repository.CalculoRepository;
import br.com.api.calculos.domain.response.CalculoHistoricoResponse;
import br.com.api.calculos.domain.vo.GenericParamIDVO;
import br.com.api.calculos.domain.vo.PaginateParansVO;
import jakarta.validation.ConstraintViolation;

/**
 * Serve o consumidor da API respondendo as requisições da camada 
 * de controllers da entidade calculo historico.
 * 
 * Na API essa entidade é apenas lida, a inserção e updates 
 * serão realizadas por outro processo, durante processamento dos calculos.
 */
@Service
public class CalculoHistoricoService {

    @Autowired
    private CalculoRepository repositoryCalc;
    
    @Autowired
    private CalculoHistReporitory repositoryCalcHist;

    @Autowired
    private CalculoHistoricoConverter converter;
    
    @Autowired
    private LocalValidatorFactoryBean validator;

    @Autowired
    private CalculoHistoricoResponse calculoHistoricoResponse;

    public ResponseEntity<?> listar(final PaginateParansVO pagVO){
    	
        Set<ConstraintViolation<PaginateParansVO>> erros = validator.validate(pagVO);

        if( !erros.isEmpty() ){
            return calculoHistoricoResponse.buildResponseErrosPaginacao(erros);
        }

        Integer vPage = Integer.valueOf(pagVO.getPage());

        final Pageable paginacao = PageRequest.of(
        		--vPage, Integer.valueOf( pagVO.getLimite() ));
        
        return ResponseEntity.ok( 
        		repositoryCalcHist.findAll(paginacao).map(converter::toVo) );
        
    }

    public ResponseEntity<?> listarPorCalculoId(final String id){

    	GenericParamIDVO idVO = new GenericParamIDVO(id);

        Set<ConstraintViolation<GenericParamIDVO>> erros = validator.validate(idVO);

        if( !erros.isEmpty() ){
            return calculoHistoricoResponse.buildResponseErrosParamId(erros);
        }
        
        final Optional<MCalculo> calcCandidato = repositoryCalc.findById( Long.valueOf(id) );

        if(!calcCandidato.isPresent()){
            return ResponseEntity.ok( List.of() );
        }

        final MCalculo mCalculo = calcCandidato.get();

        return ResponseEntity.ok(
        		mCalculo.getHistoricos()
		            .stream()
		            .map(converter::toVo)
		            .collect(Collectors.toList()) );

    }    

}
