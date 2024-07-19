package br.com.api.calculos.domain.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * VO para encapsular dados da entidade anexo
 */
@Getter
@Setter
@NoArgsConstructor
@JacksonXmlRootElement(localName = "Anexo")
public class AnexoVO implements Serializable {
    
    public static final Long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String contentType;
    private byte[] data;
    private LocalDateTime createdAt;
    private Character status;

}
