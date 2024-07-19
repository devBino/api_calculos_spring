package br.com.api.calculos.domain.vo;

import java.util.List;
import java.io.Serializable;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * VO para encapsular uma lista de registros
 * representando os Anexos
 */
@Getter
@Setter
@NoArgsConstructor
@JacksonXmlRootElement(localName = "Anexos")
@JsonRootName("Anexos")
public class ListaAnexosVO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "anexo")
    private List<AnexoVO> anexos;

    private Long totalRegistros;
    private int totalPaginas;
    
}
