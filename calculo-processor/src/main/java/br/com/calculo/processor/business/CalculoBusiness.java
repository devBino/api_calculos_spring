package br.com.calculo.processor.business;

import java.util.List;
import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.calculo.processor.model.MCalculo;
import br.com.calculo.processor.service.CalculoServiceFactory;
import br.com.calculo.processor.type.MensagemHistoricoType;
import br.com.calculo.processor.vo.MensagemProcessVO;

/**
 * Business para processamento de um calculo
 */
@Component
public class CalculoBusiness {
    
    @Autowired
    private CalculoServiceFactory factory;

    public void aplicarCalculo(final MCalculo calculo, 
        final List<MensagemProcessVO> mensagens){

        mensagens.add(new MensagemProcessVO(MensagemHistoricoType.INFO, 
            "Calculando parametros", calculo.getId()));

        final BiFunction<Double, Double, Double> fnCalc = factory.getOperation(calculo.getSinal());

        final Double resultado = fnCalc.apply(calculo.getNumero1(), calculo.getNumero2());
        calculo.setResultado(resultado);

        final StringBuilder sb = new StringBuilder()
                .append(calculo.getNumero1())
                .append(" ")
                .append(calculo.getSinal())
                .append(" ")
                .append(calculo.getNumero2())
                .append(" = ")
                .append(calculo.getResultado());

        calculo.setDescricao(sb.toString());
        
        mensagens.add(new MensagemProcessVO(MensagemHistoricoType.INFO, 
            "Calculo [" + calculo.getDescricao() + "] bem sucedido", calculo.getId()));

        calculo.setEstado('F');

    }

}
