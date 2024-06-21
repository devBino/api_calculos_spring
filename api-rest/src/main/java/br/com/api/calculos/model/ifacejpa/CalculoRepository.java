package br.com.api.calculos.model.ifacejpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.api.calculos.model.MCalculo;

/**
 * JPA para manipular entidade calculo
 */
@Repository
public interface CalculoRepository extends JpaRepository<MCalculo, Long> {

    @Query("from MCalculo where sinal =:sinal")
    List<MCalculo> findBySinal(@Param(value = "sinal") Character sinal);

    @Query("from MCalculo where calculoUU =:calculoUU")
    Optional<MCalculo> findByCalculoUU(@Param(value = "calculoUU") String calculoUU);

}
