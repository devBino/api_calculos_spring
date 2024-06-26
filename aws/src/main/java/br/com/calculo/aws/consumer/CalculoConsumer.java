package br.com.calculo.aws.consumer;

import static br.com.calculo.aws.constants.SqsConstants.*;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.calculo.aws.model.MCalculo;
import br.com.calculo.aws.model.ifacejpa.CalculoRepository;
import io.awspring.cloud.sqs.annotation.SqsListener;

/**
 * Componente responsável por ouvir a fila
 * de calculos do SQS na AWS
 */
@Component
public class CalculoConsumer {
    
    @Autowired
    private CalculoRepository repository;

    @SqsListener(QUEUE_CALCULOS)
    public void listenSqsCalculos(final Map<String, Object> params){

        //recupera a mensagem representando um cálculo a ser realizado,
        //salva na tabela de calculos, para que o processo agendado possa
        //recuperá-la pelo estado 'A', e aplique o processamento
        MCalculo mCalculo = new MCalculo( params.get("calculoUU").toString() );

        mCalculo.setDescricao("Aguardando Processamento");
        mCalculo.setResultado(0.0);
        mCalculo.setEstado('A');
        
        mCalculo.setNumero1( Double.valueOf( params.get("numero1").toString() ) );
        mCalculo.setNumero2( Double.valueOf( params.get("numero2").toString() ) );
        mCalculo.setSinal( Character.valueOf( params.get("sinal").toString().charAt(0) ) );

        repository.save(mCalculo);
        
    }

}
