package br.com.api.calculos.model.ifacejpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.api.calculos.model.MCalculoHistorico;

/**
 * JPA para manipular entidade calculo historico
 */
@Repository
public interface CalculoHistReporitory extends JpaRepository<MCalculoHistorico, Long> {

    @Query("from MCalculoHistorico where calculo =: id")
    List<MCalculoHistorico> findByCalculoId(@Param(value = "id") Long id);

}
