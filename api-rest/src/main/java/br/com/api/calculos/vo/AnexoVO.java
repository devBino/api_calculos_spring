package br.com.api.calculos.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * VO para encapsular dados da entidade anexo
 */
public class AnexoVO implements Serializable {
    
    public static final Long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String contentType;
    private byte[] data;
    private LocalDateTime createdAt;
    private Character status;

    public AnexoVO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

}
