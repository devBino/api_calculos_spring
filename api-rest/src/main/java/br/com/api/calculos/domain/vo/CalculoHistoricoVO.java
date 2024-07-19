package br.com.api.calculos.domain.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * VO para encapsular dados da entidade calculo historico
 */
@Getter
@Setter
@NoArgsConstructor
@JacksonXmlRootElement(localName = "CalculoHistorico")
@JsonRootName("CalculoHistorico")
public class CalculoHistoricoVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private Long calculoId;
    private String descricao;
    private Byte tipo;
    
}
