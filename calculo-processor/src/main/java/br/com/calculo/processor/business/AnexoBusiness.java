package br.com.calculo.processor.business;

import org.springframework.stereotype.Component;

import br.com.calculo.processor.model.MCalculo;

/**
 * Business para processamento de um calculo
 * ao processar anexos
 */
@Component
public class AnexoBusiness {
    
    public void aplicarCalculo(final MCalculo pMCalculo){

        if( pMCalculo.getSinal() == '+' ){
            pMCalculo.setResultado( pMCalculo.getNumero1() + pMCalculo.getNumero2() );
        }else if( pMCalculo.getSinal() == '-' ){
            pMCalculo.setResultado( pMCalculo.getNumero1() - pMCalculo.getNumero2() );
        }else if( pMCalculo.getSinal() == '*' ){
            pMCalculo.setResultado( pMCalculo.getNumero1() * pMCalculo.getNumero2() );
        }else if( pMCalculo.getSinal() == '/' ){
            pMCalculo.setResultado( pMCalculo.getNumero1() / pMCalculo.getNumero2() );
        }

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
