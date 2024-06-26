package br.com.api.calculos.vo;

import java.util.List;
import java.io.Serializable;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * VO para encapsular uma lista de registros
 * representando os Anexos
 */
@JacksonXmlRootElement(localName = "Anexos")
@JsonRootName("Anexos")
public class ListaAnexosVO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "anexo")
    private List<AnexoVO> anexos;

    public ListaAnexosVO(){}

    public List<AnexoVO> getAnexos() {
        return anexos;
    }

    public void setAnexos(List<AnexoVO> anexos) {
        this.anexos = anexos;
    }

}
