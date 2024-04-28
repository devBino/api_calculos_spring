package br.com.calculo.processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.calculo.processor.model.MCalculo;

/**
 * Repositorio para manipular entidade calculo
 */
@Repository
public interface CalculoRepository extends JpaRepository<MCalculo, Long> {}
