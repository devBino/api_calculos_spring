package br.com.api.calculos.bo;

import java.io.Serializable;

/**
 * * BO para encapsular dados da entidade anexo historico
 */
public class AnexoHistoricoBO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long anexoId;
    private String descricao;
    private Byte tipo;

    public AnexoHistoricoBO(){}

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
