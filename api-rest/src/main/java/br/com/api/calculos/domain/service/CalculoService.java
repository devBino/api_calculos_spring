package br.com.api.calculos.domain.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import br.com.api.calculos.domain.converter.CalculoConverter;
import br.com.api.calculos.domain.model.MCalculo;
import br.com.api.calculos.domain.repository.CalculoRepository;
import br.com.api.calculos.domain.response.CalculoResponse;
import br.com.api.calculos.domain.vo.CalculoVO;
import br.com.api.calculos.domain.vo.GenericParamIDVO;
import br.com.api.calculos.domain.vo.ListaCalculosVO;
import br.com.api.calculos.domain.vo.PaginateParansVO;
import br.com.api.calculos.type.SinalCalculoType;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import jakarta.validation.ConstraintViolation;

/**
 * Serve o consumidor da API respondendo as requisições da camada 
 * de controllers da entidade calculo
 */
@Service
public class CalculoService {
    
    @Autowired
    private CalculoRepository repository;

    @Autowired
    private CalculoConverter converter;

    @Autowired
    private SqsTemplate sqsTemplate;
    
    @Autowired
    private CalculoResponse calculoResponse;
    
    @Autowired
    private LocalValidatorFactoryBean validator;

    public ResponseEntity<?> criar(CalculoVO body){

    	Set<ConstraintViolation<CalculoVO>> erros = validator.validate(body);

        if( !erros.isEmpty() ){
            return calculoResponse.buildResponseErros(erros);
        }

        MCalculo mCalculo = converter.toModel(body);

        mCalculo.setDescricao("Aguardando Processamento");
        mCalculo.setResultado(0.0);
        mCalculo.setEstado('A');

        return ResponseEntity.ok( 
        		converter.toVo((MCalculo) repository.save(mCalculo)) );

    }

    public ResponseEntity<?> criarCalculoAws(CalculoVO body){
        
    	Set<ConstraintViolation<CalculoVO>> erros = validator.validate(body);

        if( !erros.isEmpty() ){
            return calculoResponse.buildResponseErros(erros);
        }

        final String calculoUU = UUID.randomUUID().toString();
        
        body.setCalculoUU(calculoUU);

        final Map<String, Object> params = new HashMap<>();

        params.put("sinal", body.getSinal());
        params.put("numero1", body.getNumero1());
        params.put("numero2", body.getNumero2());
        params.put("calculoUU", calculoUU);
        
        String SQS = "https://localhost.localstack.cloud:4566/000000000000/queue-calculos";
        
        sqsTemplate.send(SQS, params);

        return ResponseEntity.ok( body );

    }

    public ResponseEntity<?> atualizar(CalculoVO body){

    	if( Objects.isNull(body.getId()) ){
            return calculoResponse.buildResponseErros(Map.of("id", "campo obrigatório"));
        }

        Set<ConstraintViolation<CalculoVO>> erros = validator.validate(body);

        if( !erros.isEmpty() ){
            return calculoResponse.buildResponseErros(erros);
        }

        final Optional<MCalculo> mdCandidato = repository.findById(body.getId());

        if( !mdCandidato.isPresent() ){
            return ResponseEntity.ok( new CalculoVO() );
        }

        final MCalculo mCalculo = mdCandidato.get();

        mCalculo.setNumero1( !Objects.isNull(body.getNumero1()) ? body.getNumero1() : mCalculo.getNumero1() );
        mCalculo.setNumero2( !Objects.isNull(body.getNumero2()) ? body.getNumero2() : mCalculo.getNumero2() );
        mCalculo.setSinal( !Objects.isNull(body.getSinal()) ? body.getSinal() : mCalculo.getSinal() );

        mCalculo.setDescricao("Aguardando Processamento");
        mCalculo.setResultado(0.0);
        mCalculo.setEstado('A');

        return ResponseEntity.ok( 
        		converter.toVo((MCalculo) repository.save(mCalculo)) );

    }

    public ResponseEntity<?> listar(final PaginateParansVO paramsVO) {
    	
        Set<ConstraintViolation<PaginateParansVO>> erros = validator.validate(paramsVO);

        if( !erros.isEmpty() ){
            return calculoResponse.buildResponseErrosPaginacao(erros);
        }

        Integer vPage = Integer.valueOf(paramsVO.getPage());
        
        Pageable paginacao = PageRequest.of(
        		--vPage, Integer.valueOf( paramsVO.getLimite() ));

        final Page<CalculoVO> calculos = repository
            .findAll(paginacao)
            .map(converter::toVo);

        final ListaCalculosVO lista = new ListaCalculosVO();
        
        lista.setCalculos(calculos.getContent());
        lista.setTotalPaginas(calculos.getTotalPages());
        lista.setTotalRegistros(calculos.getTotalElements());
        
        return ResponseEntity.ok( lista );

    }

    public ResponseEntity<?> listarPorSinal(final String sinal, final PaginateParansVO paramsVO){

    	Set<ConstraintViolation<PaginateParansVO>> erros = validator.validate(paramsVO);

        if( !erros.isEmpty() ){
            return calculoResponse.buildResponseErrosPaginacao(erros);
        }
        
        SinalCalculoType sinalCalcType = SinalCalculoType.fromCodigo(sinal);

        if(Objects.isNull(sinalCalcType)){
            return calculoResponse.buildResponseErros(Map.of("Sinal", "Sinal inválido enviado, envie os sinais [adi, sub, mul, div]"));
        }

        Integer vPage = Integer.valueOf(paramsVO.getPage());
        
        Pageable paginacao = PageRequest.of(
        		--vPage, Integer.valueOf( paramsVO.getLimite() ));
        
        final Page<CalculoVO> calculos = repository
            .findBySinal(sinalCalcType.getSinal(), paginacao)
            .map(converter::toVo);

        final ListaCalculosVO lista = new ListaCalculosVO();

        lista.setCalculos(calculos.getContent());
        lista.setTotalPaginas(calculos.getTotalPages());
        lista.setTotalRegistros(calculos.getTotalElements());

        return ResponseEntity.ok( lista );

    }

    public ResponseEntity<?> listarPorAnexo(final String anexoId, final PaginateParansVO pagVO){

        Set<ConstraintViolation<PaginateParansVO>> erros = validator.validate(pagVO);

        if( !erros.isEmpty() ){
            return calculoResponse.buildResponseErrosPaginacao(erros);
        }

        GenericParamIDVO idVO = new GenericParamIDVO(anexoId);

        Set<ConstraintViolation<GenericParamIDVO>> errosAnexoId = validator.validate(idVO);

        if( !errosAnexoId.isEmpty() ){
            return calculoResponse.buildResponseErrosParamId(errosAnexoId);
        }

        Integer vPage = Integer.valueOf(pagVO.getPage());
        
        Pageable paginacao = PageRequest.of(
        		--vPage, Integer.valueOf( pagVO.getLimite() ));

        final Page<CalculoVO> calculos = repository
            .findByIdAnexo(Long.valueOf(anexoId), paginacao)
            .map(converter::toVo);

        final ListaCalculosVO lista = new ListaCalculosVO();

        lista.setCalculos(calculos.getContent());
        lista.setTotalPaginas(calculos.getTotalPages());
        lista.setTotalRegistros(calculos.getTotalElements());

        return ResponseEntity.ok( lista );

    }

    public ResponseEntity<?> detalhar(final String id){
        
    	GenericParamIDVO idVO = new GenericParamIDVO(id);

        Set<ConstraintViolation<GenericParamIDVO>> erros = validator.validate(idVO);

        if( !erros.isEmpty() ){
            return calculoResponse.buildResponseErrosParamId(erros);
        }
        
        final Optional<MCalculo> mdCandidato = repository.findById( Long.valueOf(id) );

        if( !mdCandidato.isPresent() ){
            return ResponseEntity.ok( new CalculoVO() );
        }

        return ResponseEntity.ok( converter.toVo(mdCandidato.get()) );

    }

    public ResponseEntity<?> detalharCalculoAws(final String calculoUU){
        
    	if(Objects.isNull(calculoUU) || calculoUU.isEmpty() || calculoUU.isBlank()){
            return calculoResponse.buildResponseErros(Map.of("CalculoUU", "Campo Obrigatório e deve conter valor"));
        }
    	
        final Optional<MCalculo> calculoCandidato = repository.findByCalculoUU(calculoUU);

        if( !calculoCandidato.isPresent() ){
            return ResponseEntity.ok( new CalculoVO() );
        }

        return ResponseEntity.ok( converter.toVo(calculoCandidato.get()) );

    }

    public ResponseEntity<?> deletar(String id){

        GenericParamIDVO idVO = new GenericParamIDVO(id);

        Set<ConstraintViolation<GenericParamIDVO>> erros = validator.validate(idVO);

        if( !erros.isEmpty() ){
            return calculoResponse.buildResponseErrosParamId(erros);
        }
        
        repository.deleteById(Long.valueOf(id));
        
        return ResponseEntity.noContent().build();
        
    }

}
