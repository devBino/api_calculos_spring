package br.com.api.calculos.domain.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade do banco de dados representando usuarios
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_usuarios")
public class MUsuario implements Serializable {
    
    public static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", unique = true, length = 100)
    private String nome;

    @Column(name = "user", unique = true, length = 30)
    private String user;

    /**
     * Coluna com tamanho 150 por conta armazenar uma hash da senha
     */
    @Column(name = "password", unique = true, length = 150)
    private String password;

    private int ativo;

}
