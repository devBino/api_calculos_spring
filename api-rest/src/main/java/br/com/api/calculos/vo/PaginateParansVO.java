package br.com.api.calculos.vo;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * VO para encapsular parametros recebidos para paginação
 * de registros, e apoiar na validação dos valores recebidos
 */
public class PaginateParansVO implements Serializable {
    
    public static final Long serialVersionUID = 1L;

    @NotEmpty(message = "parametro page não pode ser vazio")
    @NotNull(message = "parametro page não pode ser nulo")
    @Positive(message = "parametro page deve ser numérico e positivo")
    private String page;

    @NotEmpty(message = "parametro limit não pode ser vazio")
    @NotNull(message = "parametro limit não pode ser nulo")
    @Positive(message = "parametro limit deve ser numérico e positivo")
    private String limite;

    public PaginateParansVO(String page, String limite) {
        this.page = page;
        this.limite = limite;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getLimite() {
        return limite;
    }

    public void setLimite(String limite) {
        this.limite = limite;
    }

}
