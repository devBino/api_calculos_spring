package br.com.calculo.processor.model;

import java.io.Serializable;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Entidade do banco de dados representando calculo
 */
@Getter
@Setter
@Entity
@Table(name = "tb_calculos")
public class MCalculo implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;
    
    @Column(name = "calculoUU", length = 255, unique = true)
    private String calculoUU;

    @Column(name = "descricao", length = 100)
    private String descricao;

    @Column(name = "numero1", nullable = false)
    private Double numero1;

    @Column(name = "numero2", nullable = false)
    private Double numero2;

    @Column(name = "resultado")
    private Double resultado;

    @Column(name = "sinal", nullable = false)
    private Character sinal;

    @Column(name = "estado", nullable = false)
    private Character estado;

    @OneToMany(mappedBy = "calculo", cascade = CascadeType.ALL)
    private List<MCalculoHistorico> hisoricos;

    @Column(name = "anexo_id")
    private Long anexoId;

    public MCalculo(){
        if( calculoUU == null ){
            calculoUU = UUID.randomUUID().toString();
        }
    }

    public MCalculo(String calculoUU){
        this.calculoUU = calculoUU;
    }

}
