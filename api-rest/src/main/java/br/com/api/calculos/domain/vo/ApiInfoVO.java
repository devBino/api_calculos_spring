package br.com.api.calculos.domain.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * VO para encapsular propriedades da entidade ApiInfo
 */
@Getter
@Setter
public class ApiInfoVO implements Serializable {
    
    public static final Long serialVersionUID = 1L;

    private String autor;
    private String repositorio;
    private String versao;
    private String descricao;

    public ApiInfoVO(){
        autor = "Fernando Bino Machado";
        repositorio = "https://github.com/devBino";
        versao = "1";
        descricao = "Api Rest para Receber parâmetros de Calculos";
    }

}
