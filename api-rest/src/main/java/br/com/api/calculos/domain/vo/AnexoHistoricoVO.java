package br.com.api.calculos.domain.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * VO para encapsular dados da entidade anexo historico
 */
@Getter
@Setter
@NoArgsConstructor
public class AnexoHistoricoVO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long anexoId;
    private String descricao;
    private Byte tipo;

}
