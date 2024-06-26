package br.com.api.calculos.vo;

import java.util.List;
import java.io.Serializable;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * VO para encapsular uma lista de registros
 * representando os Calculos
 */
@JacksonXmlRootElement(localName = "Calculos")
@JsonRootName("Calculos")
public class ListaCalculosVO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "calculo")
    private List<CalculoVO> calculos;

    public ListaCalculosVO(){}

    public List<CalculoVO> getCalculos() {
        return calculos;
    }

    public void setCalculos(List<CalculoVO> calculos) {
        this.calculos = calculos;
    }

}
