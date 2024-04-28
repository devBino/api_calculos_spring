package br.com.calculo.processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.calculo.processor.model.MCalculo;

/**
 * Repositorio para manipular entidade calculo
 */
@Repository
public interface CalculoRepository extends JpaRepository<MCalculo, Long> {

    @Query("from MCalculo where estado =:estado order by id limit 1")
    MCalculo findByEstado(@Param(value = "estado") Character estado);

}
