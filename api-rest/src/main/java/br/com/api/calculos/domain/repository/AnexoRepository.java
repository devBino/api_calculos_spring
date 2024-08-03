package br.com.api.calculos.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.api.calculos.domain.model.MAnexo;

/**
 * JPA para manipular entidade anexo
 */
@ResponseBody
public interface AnexoRepository extends JpaRepository<MAnexo, Long> {}