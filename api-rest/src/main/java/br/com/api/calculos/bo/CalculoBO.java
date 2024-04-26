package br.com.api.calculos.bo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * BO para encapsular dados da entidade calculo
 */
@JsonPropertyOrder({
    "id",
    "numero1",
    "numero2",
    "sinal",
    "resultado",
    "descricao",
    "estado"
})
public class CalculoBO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Long id;
    private String descricao;
    private Double numero1;
    private Double numero2;
    private Double resultado;
    private Character sinal;
    private Character estado;

    public CalculoBO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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