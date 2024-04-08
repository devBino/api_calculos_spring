package br.com.calculadora.bo.v1;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
    "id",
    "sinal",
    "valor1",
    "valor2",
    "resultado",
    "estado"
})
public class CalculoBO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Long id;

    @JsonProperty("valor1")
    private Double numero1;

    @JsonProperty("valor2")
    private Double numero2;

    // @JsonIgnore
    private Double resultado;
    private Character sinal;
    private Character estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
