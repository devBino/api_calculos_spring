package br.com.api.calculos.bo;

import java.io.Serializable;

public class CalculoHistoricoBO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private Long calculoId;
    private String descricao;
    private Byte tipo;

    public CalculoHistoricoBO(){}

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
