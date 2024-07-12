package br.com.api.calculos.service;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.api.calculos.converter.CalculoConverter;
import br.com.api.calculos.model.MCalculo;
import br.com.api.calculos.model.ifacejpa.CalculoRepository;
import br.com.api.calculos.type.SinalCalculoType;
import br.com.api.calculos.vo.CalculoVO;
import br.com.api.calculos.vo.ListaCalculosVO;
import io.awspring.cloud.sqs.operations.SqsTemplate;

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

    public CalculoVO criar(CalculoVO body){

        MCalculo mCalculo = converter.toModel(body);

        mCalculo.setDescricao("Aguardando Processamento");
        mCalculo.setResultado(0.0);
        mCalculo.setEstado('A');

        return converter.toVo((MCalculo) repository.save(mCalculo));

    }

    public CalculoVO criarCalculoAws(CalculoVO body){
        
        final String calculoUU = UUID.randomUUID().toString();
        
        body.setCalculoUU(calculoUU);

        final Map<String, Object> params = new HashMap<>();

        params.put("sinal", body.getSinal());
        params.put("numero1", body.getNumero1());
        params.put("numero2", body.getNumero2());
        params.put("calculoUU", calculoUU);
        
        String SQS = "https://localhost.localstack.cloud:4566/000000000000/queue-calculos";
        
        sqsTemplate.send(SQS, params);

        return body;

    }

    public CalculoVO atualizar(CalculoVO body){

        final Optional<MCalculo> mdCandidato = repository.findById(body.getId());

        if( !mdCandidato.isPresent() ){
            return new CalculoVO();
        }

        final MCalculo mCalculo = mdCandidato.get();

        mCalculo.setNumero1( !Objects.isNull(body.getNumero1()) ? body.getNumero1() : mCalculo.getNumero1() );
        mCalculo.setNumero2( !Objects.isNull(body.getNumero2()) ? body.getNumero2() : mCalculo.getNumero2() );
        mCalculo.setSinal( !Objects.isNull(body.getSinal()) ? body.getSinal() : mCalculo.getSinal() );

        mCalculo.setDescricao("Aguardando Processamento");
        mCalculo.setResultado(0.0);
        mCalculo.setEstado('A');

        return converter.toVo((MCalculo) repository.save(mCalculo));

    }

    public ListaCalculosVO listar(final Pageable paginacao){
        
        final Page<CalculoVO> calculos = repository
            .findAll(paginacao)
            .map(converter::toVo);

        final ListaCalculosVO lista = new ListaCalculosVO();
        
        lista.setCalculos(calculos.getContent());
        lista.setTotalPaginas(calculos.getTotalPages());
        lista.setTotalRegistros(calculos.getTotalElements());
        
        return lista;

    }

    public ListaCalculosVO listarPorSinal(final char sinal, final Pageable paginacao){

        final Page<CalculoVO> calculos = repository
            .findBySinal(sinal, paginacao)
            .map(converter::toVo);

        final ListaCalculosVO lista = new ListaCalculosVO();

        lista.setCalculos(calculos.getContent());
        lista.setTotalPaginas(calculos.getTotalPages());
        lista.setTotalRegistros(calculos.getTotalElements());

        return lista;

    }

    public ListaCalculosVO listarPorAnexo(final Long anexoId, final Pageable paginacao){

        final Page<CalculoVO> calculos = repository
            .findByIdAnexo(anexoId, paginacao)
            .map(converter::toVo);

        final ListaCalculosVO lista = new ListaCalculosVO();

        lista.setCalculos(calculos.getContent());
        lista.setTotalPaginas(calculos.getTotalPages());
        lista.setTotalRegistros(calculos.getTotalElements());

        return lista;

    }

    public CalculoVO detalhar(final Long id){
        
        final Optional<MCalculo> mdCandidato = repository.findById(id);

        if( !mdCandidato.isPresent() ){
            return new CalculoVO();
        }

        return converter.toVo(mdCandidato.get());

    }

    public CalculoVO detalharCalculoAws(final String calculoUU){
        
        final Optional<MCalculo> calculoCandidato = repository.findByCalculoUU(calculoUU);

        if( !calculoCandidato.isPresent() ){
            return new CalculoVO();
        }

        return converter.toVo(calculoCandidato.get());

    }

    public void deletar(Long id){
        repository.deleteById(id);
    }

}
