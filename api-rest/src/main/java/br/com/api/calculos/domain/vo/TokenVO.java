package br.com.api.calculos.domain.vo;

import java.io.Serializable;

/**
 * VO para encapsular dados do Token gerado
 * que será devolvido nas rotinas de autenticação
 */
public class TokenVO implements Serializable {
    
    public static final Long serialVersionUID = 1L;

    private String token;
    private String mensagem;
    private boolean sucesso;

    public TokenVO(){}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

}
