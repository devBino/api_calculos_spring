package br.com.api.calculos.domain.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import jakarta.validation.constraints.NotNull;

/**
 * VO para encapsular dados da entidade calculo
 */
@JacksonXmlRootElement(localName = "Calculo")
@JsonPropertyOrder({
    "id",
    "anexoId",
    "numero1",
    "numero2",
    "sinal",
    "resultado",
    "descricao",
    "estado"
})
public class CalculoVO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long anexoId;
    private String calculoUU;
    private String descricao;

    @NotNull
    private Double numero1;
    
    @NotNull
    private Double numero2;

    private Double resultado;

    @NotNull
    private Character sinal;

    private Character estado;

    public CalculoVO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAnexoId() {
        return anexoId;
    }

    public void setAnexoId(Long anexoId) {
        this.anexoId = anexoId;
    }

    public String getCalculoUU() {
        return calculoUU;
    }

    public void setCalculoUU(String calculoUU) {
        this.calculoUU = calculoUU;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getNumero1() {
        return numero1;
    }

    public void setNumero1(Double numero1) {
        this.numero1 = numero1;
    }

    public Double getNumero2() {
        return numero2;
    }

    public void setNumero2(Double numero2) {
        this.numero2 = numero2;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }

    public Character getSinal() {
        return sinal;
    }

    public void setSinal(Character sinal) {
        this.sinal = sinal;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

}
