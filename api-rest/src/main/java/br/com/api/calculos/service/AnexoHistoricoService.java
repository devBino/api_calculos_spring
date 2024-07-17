package br.com.api.calculos.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.api.calculos.converter.AnexoHistoricoConverter;
import br.com.api.calculos.model.MAnexo;
import br.com.api.calculos.repository.AnexoHistRepository;
import br.com.api.calculos.repository.AnexoRepository;
import br.com.api.calculos.vo.AnexoHistoricoVO;

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
    private AnexoHistoricoConverter converter;

    public Page<AnexoHistoricoVO> listar(final Pageable paginacao){
        return anexoHistRepository
            .findAll(paginacao)
            .map(converter::toVo);
    }

    public List<AnexoHistoricoVO> listarPorAnexoId(final Long id){

        final Optional<MAnexo> anexoCandidato = anexoRepository.findById(id);

        if(!anexoCandidato.isPresent()){
            return List.of();
        }

        final MAnexo mAnexo = anexoCandidato.get();

        return mAnexo.getHistoricos()
            .stream()
            .map(converter::toVo)
            .collect(Collectors.toList());

    }

}
