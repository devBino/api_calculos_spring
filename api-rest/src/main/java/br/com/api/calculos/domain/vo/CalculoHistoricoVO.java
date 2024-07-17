package br.com.api.calculos.domain.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * VO para encapsular dados da entidade calculo historico
 */
@JacksonXmlRootElement(localName = "CalculoHistorico")
@JsonRootName("CalculoHistorico")
public class CalculoHistoricoVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private Long calculoId;
    private String descricao;
    private Byte tipo;

    public CalculoHistoricoVO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCalculoId() {
        return calculoId;
    }

    public void setCalculoId(Long calculoId) {
        this.calculoId = calculoId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Byte getTipo() {
        return tipo;
    }

    public void setTipo(Byte tipo) {
        this.tipo = tipo;
    }
    
}
