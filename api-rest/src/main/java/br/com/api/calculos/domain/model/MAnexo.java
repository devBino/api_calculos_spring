package br.com.api.calculos.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade do banco de dados representando anexos
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_anexos")
public class MAnexo implements Serializable {
    
    public static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, length = 100)
    private String name;
    private String contentType;
    private byte[] data;
    private LocalDateTime createdAt;
    private Character status;

    @OneToMany(mappedBy = "anexo", cascade = CascadeType.ALL)
    private List<MAnexoHistorico> historicos;

}
