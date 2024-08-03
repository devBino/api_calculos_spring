package br.com.api.calculos.domain.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * VO para encapsular dados da entidade calculo
 */
@Getter
@Setter
@NoArgsConstructor
@JacksonXmlRootElement(localName = "Calculo")
@JsonPropertyOrder({
    "id",
    "anexoId",
    "numero1",
    "numero2",
    "sinal",
    "resultado",
    "descricao",
    "estado"
})
public class CalculoVO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long anexoId;
    private String calculoUU;
    private String descricao;

    @NotNull
    private Double numero1;
    
    @NotNull
    private Double numero2;

    private Double resultado;

    @NotNull
    private Character sinal;

    private Character estado;

}
