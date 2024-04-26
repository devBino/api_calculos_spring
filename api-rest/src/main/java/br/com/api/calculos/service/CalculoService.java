package br.com.api.calculos.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.api.calculos.bo.CalculoBO;
import br.com.api.calculos.converter.CalculoConverter;
import br.com.api.calculos.model.MCalculo;
import br.com.api.calculos.repository.CalculoRepository;
import br.com.api.calculos.type.SinalCalculoType;

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

    public CalculoBO criar(CalculoBO body){

        MCalculo mCalculo = converter.toModel(body);

        mCalculo.setDescricao("Aguardando Processamento");
        mCalculo.setResultado(0.0);
        mCalculo.setEstado('A');

        return converter.toBo((MCalculo) repository.save(mCalculo));

    }

    public CalculoBO atualizar(CalculoBO body){

        final Optional<MCalculo> mdCandidato = repository.findById(body.getId());

        if( !mdCandidato.isPresent() ){
            return new CalculoBO();
        }

        final MCalculo mCalculo = mdCandidato.get();

        mCalculo.setNumero1( !Objects.isNull(body.getNumero1()) ? body.getNumero1() : mCalculo.getNumero1() );
        mCalculo.setNumero2( !Objects.isNull(body.getNumero2()) ? body.getNumero2() : mCalculo.getNumero2() );
        mCalculo.setSinal( !Objects.isNull(body.getSinal()) ? body.getSinal() : mCalculo.getSinal() );

        mCalculo.setDescricao("Aguardando Processamento");
        mCalculo.setResultado(0.0);
        mCalculo.setEstado('A');

        return converter.toBo((MCalculo) repository.save(mCalculo));

    }

    public Page<CalculoBO> listar(final Pageable paginacao){
        
        final Page<CalculoBO> calculos = repository
            .findAll(paginacao)
            .map(converter::toBo);

        return calculos;

    }

    public List<CalculoBO> listarPorSinal(final Byte sinal){

        SinalCalculoType sinalCalcType = SinalCalculoType.fromIndice(sinal);

        if(Objects.isNull(sinalCalcType)){
            sinalCalcType = SinalCalculoType.ADICAO;
        }

        return repository
            .findBySinal(sinalCalcType.getSinal())
            .stream()
            .map(converter::toBo)
            .collect(Collectors.toList());

    }

    public CalculoBO detalhar(final Long id){
        
        final Optional<MCalculo> mdCandidato = repository.findById(id);

        if( !mdCandidato.isPresent() ){
            return new CalculoBO();
        }

        return converter.toBo(mdCandidato.get());

    }

    public void deletar(Long id){
        repository.deleteById(id);
    }

}
