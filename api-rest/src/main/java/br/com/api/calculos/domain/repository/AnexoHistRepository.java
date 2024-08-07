package br.com.api.calculos.domain.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.api.calculos.domain.model.MAnexoHistorico;

/**
 * JPA para manipular entidade anexo historico
 */
@Repository
public interface AnexoHistRepository extends JpaRepository<MAnexoHistorico, Long> {
    
    @Query("from MAnexoHistorico where anexo =: id")
    List<MAnexoHistorico> findByAnexoId(@Param(value = "id") Long id);

}
