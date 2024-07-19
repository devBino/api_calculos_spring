package br.com.calculo.processor.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade do banco de dados representando anexo historico
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_anexohist")
public class MAnexoHistorico implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "anexoId")
    private MAnexo anexo;

    private String descricao;

    private Byte tipo;

}
