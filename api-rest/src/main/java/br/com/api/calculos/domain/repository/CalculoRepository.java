package br.com.api.calculos.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.api.calculos.domain.model.MCalculo;

/**
 * JPA para manipular entidade calculo
 */
@Repository
public interface CalculoRepository extends JpaRepository<MCalculo, Long> {

    @Query("from MCalculo where sinal =:sinal")
    Page<MCalculo> findBySinal(@Param(value = "sinal") Character sinal, Pageable paginacao);

    @Query("from MCalculo where anexoId =:anexoId")
    Page<MCalculo> findByIdAnexo(@Param(value = "anexoId") Long anexoId, Pageable paginacao);

    @Query("from MCalculo where calculoUU =:calculoUU")
    Optional<MCalculo> findByCalculoUU(@Param(value = "calculoUU") String calculoUU);

}
