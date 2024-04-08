package br.com.calculadora.service.v1;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.calculadora.bo.v1.CalculoBO;
import br.com.calculadora.converter.v1.CalculoConverter;
import br.com.calculadora.model.CalculoRepository;
import br.com.calculadora.model.MCalculo;

@Service
public class CalculoServiceV1 {

    @Autowired
    private CalculoRepository repository;

    private void aplicarCalculo(final CalculoBO pCalculoBO){

        if( pCalculoBO.getSinal() == '+' ){
            pCalculoBO.setResultado( pCalculoBO.getNumero1() + pCalculoBO.getNumero2() );
        }else if( pCalculoBO.getSinal() == '-' ){
            pCalculoBO.setResultado( pCalculoBO.getNumero1() - pCalculoBO.getNumero2() );
        }else if( pCalculoBO.getSinal() == '*' ){
            pCalculoBO.setResultado( pCalculoBO.getNumero1() * pCalculoBO.getNumero2() );
        }else if( pCalculoBO.getSinal() == '/' ){
            pCalculoBO.setResultado( pCalculoBO.getNumero1() / pCalculoBO.getNumero2() );
        }

    }

    public CalculoBO criar(final CalculoBO pBody){
        
        MCalculo m = CalculoConverter.toModel(pBody);

        aplicarCalculo(pBody);

        m.setEstado('A');

        repository.save(m);
        
        return CalculoConverter.toBO(m);

    }

    public List<CalculoBO> listarCalculos(){

        return repository.findAll()
            .stream()
            .map(CalculoConverter::toBO)
            .collect(Collectors.toList());

    }

    public CalculoBO getById(final int pId){
        
        final Optional<MCalculo> mCandidato = repository.findAll()
            .stream()
            .filter(m -> m.getId() == pId)
            .findFirst();

        if( !mCandidato.isPresent() ){
            return new CalculoBO();
        }

        return CalculoConverter.toBO(mCandidato.get());

    }

    public CalculoBO atualizar(final CalculoBO pBody){

        MCalculo m = CalculoConverter.toModel(pBody);

        m.setEstado('A');

        repository.save(m);

        return getById(pBody.getId().intValue());

    }

    public void deletar(final int pId){
        
        MCalculo m = new MCalculo();
        m.setId((long)pId);
        
        repository.delete(m);

    }

}
