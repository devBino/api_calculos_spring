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
 * representando os Calculos
 */
@Getter
@Setter
@NoArgsConstructor
@JacksonXmlRootElement(localName = "Calculos")
@JsonRootName("Calculos")
public class ListaCalculosVO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "calculo")
    private List<CalculoVO> calculos;

    private Long totalRegistros;
    private int totalPaginas;

}
