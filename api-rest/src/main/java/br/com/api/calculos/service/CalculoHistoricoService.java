package br.com.api.calculos.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.calculos.bo.CalculoHistoricoBO;
import br.com.api.calculos.converter.CalculoHistoricoConverter;
import br.com.api.calculos.model.MCalculo;
import br.com.api.calculos.repository.CalculoHistReporitory;
import br.com.api.calculos.repository.CalculoRepository;

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

    public List<CalculoHistoricoBO> listar(){
        return repositoryCalcHist.findAll().stream().map(converter::toBo).collect(Collectors.toList());
    }

    public List<CalculoHistoricoBO> listarPorCalculoId(final Long id){

        final Optional<MCalculo> calcCandidato = repositoryCalc.findById(id);

        if(!calcCandidato.isPresent()){
            return List.of();
        }

        final MCalculo mCalculo = calcCandidato.get();

        return mCalculo.getHistoricos()
            .stream()
            .map(converter::toBo)
            .collect(Collectors.toList());

    }    

}
