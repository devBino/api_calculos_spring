package br.com.api.calculos.domain.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * VO para encapsular dados da entidade usuario
 */
@Getter
@Setter
@NoArgsConstructor
public class UsuarioVO implements Serializable {
    
    public static final Long serialVersionUID = 1L;

    private Long id;

    private String nome;
    private String user;
    private String password;
    private int ativo;

}
