package br.com.calculo.processor.business;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.calculo.processor.model.MCalculo;
import br.com.calculo.processor.type.MensagemHistoricoType;
import br.com.calculo.processor.vo.MensagemProcessVO;

/**
 * Business para processamento de um calculo
 */
@Component
public class CalculoBusiness {
    
    public void aplicarCalculo(final MCalculo calculo, 
        final List<MensagemProcessVO> mensagens){

        mensagens.add(new MensagemProcessVO(MensagemHistoricoType.INFO, 
            "Calculando parametros", calculo.getId()));

        if( calculo.getSinal() == '+' ){
            calculo.setResultado( calculo.getNumero1() + calculo.getNumero2() );
        }else if( calculo.getSinal() == '-' ){
            calculo.setResultado( calculo.getNumero1() - calculo.getNumero2() );
        }else if( calculo.getSinal() == '*' ){
            calculo.setResultado( calculo.getNumero1() * calculo.getNumero2() );
        }else if( calculo.getSinal() == '/' ){
            calculo.setResultado( calculo.getNumero1() / calculo.getNumero2() );
        }

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
