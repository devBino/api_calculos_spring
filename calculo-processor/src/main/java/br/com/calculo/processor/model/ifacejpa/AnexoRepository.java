package br.com.calculo.processor.model.ifacejpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.calculo.processor.model.MAnexo;

/**
 * Repositorio para manipular entidade anexo
 */
@Repository
public interface AnexoRepository extends JpaRepository<MAnexo, Long> {

    @Query("from MAnexo where status = :status order by id limit 1")
    MAnexo getLastFileWaitingProcess(@Param("status") Character status);

}
