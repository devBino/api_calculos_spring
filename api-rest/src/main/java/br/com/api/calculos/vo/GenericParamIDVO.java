package br.com.api.calculos.vo;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * VO para encapsular ids recebidos em requests que usam ids, 
 * e apoiar na validação do id
 */
public class GenericParamIDVO implements Serializable {
    
    public static final Long serialVersionUID = 1L;

    @NotEmpty(message = "parametro id não pode ser vazio")
    @NotNull(message = "parametro id não pode ser nulo")
    @Positive(message = "parametro id deve ser numérico e positivo")
    private String id;

    public GenericParamIDVO(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
