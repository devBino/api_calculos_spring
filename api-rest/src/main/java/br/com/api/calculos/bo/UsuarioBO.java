package br.com.api.calculos.bo;

import java.io.Serializable;

/**
 * BO para encapsular dados da entidade usuario
 */
public class UsuarioBO implements Serializable {
    
    public static final Long serialVersionUID = 1L;

    private Long id;

    private String nome;
    private String user;
    private String password;
    private int ativo;

    public UsuarioBO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

}
