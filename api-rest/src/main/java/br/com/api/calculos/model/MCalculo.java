package br.com.api.calculos.model;

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

/**
 * Entidade do banco de dados representando calculo
 */
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

    @Column(name = "anexo_id")
    private Long anexoId;

    @OneToMany(mappedBy = "calculo", cascade = CascadeType.ALL)
    private List<MCalculoHistorico> historicos;

    public MCalculo(){
        if( calculoUU == null ){
            calculoUU = UUID.randomUUID().toString();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalculoUU() {
        return calculoUU;
    }

    public void setCalculoUU(String calculoUU) {
        if( this.calculoUU == null ){
            this.calculoUU = calculoUU;
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getNumero1() {
        return numero1;
    }

    public void setNumero1(Double numero1) {
        this.numero1 = numero1;
    }

    public Double getNumero2() {
        return numero2;
    }

    public void setNumero2(Double numero2) {
        this.numero2 = numero2;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }

    public Character getSinal() {
        return sinal;
    }

    public void setSinal(Character sinal) {
        this.sinal = sinal;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public List<MCalculoHistorico> getHistoricos() {
        return historicos;
    }

    public Long getAnexoId() {
        return anexoId;
    }

    public void setAnexoId(Long anexoId) {
        this.anexoId = anexoId;
    }

}
