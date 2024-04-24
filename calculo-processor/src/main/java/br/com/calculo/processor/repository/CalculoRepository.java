package br.com.calculo.processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.calculo.processor.model.MCalculo;

public interface CalculoRepository extends JpaRepository<MCalculo, Long> {}
