package br.com.calculo.processor.business;

import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.calculo.processor.service.CalculoServiceFactory;
import br.com.calculo.processor.model.MCalculo;

/**
 * Business para processamento de um calculo
 * ao processar anexos
 */
@Component
public class AnexoBusiness {
    
    @Autowired
    private CalculoServiceFactory factory;

    public void aplicarCalculo(final MCalculo pMCalculo){

        final BiFunction<Double, Double, Double> fnCalc = factory.getOperation(pMCalculo.getSinal());

        final Double resultado = fnCalc.apply(pMCalculo.getNumero1(), pMCalculo.getNumero2());
        pMCalculo.setResultado(resultado);

        final StringBuilder sb = new StringBuilder()
                .append(pMCalculo.getNumero1())
                .append(" ")
                .append(pMCalculo.getSinal())
                .append(" ")
                .append(pMCalculo.getNumero2())
                .append(" = ")
                .append(pMCalculo.getResultado());

                
        pMCalculo.setDescricao(sb.toString());
        
        pMCalculo.setEstado('F');

    }

}
