package br.com.calculadora.processo;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.calculadora.model.CalculoRepository;
import br.com.calculadora.model.MCalculo;

@Component
public class CalculoProcessor {
    
    private Thread processo;

    @Autowired
    private CalculoRepository repository;

    public void aplicarCalculo(final MCalculo mCalculo){
        processo = new Thread(new TaskCalculoProcessor(mCalculo, repository));
        processo.start();
    }

}

class TaskCalculoProcessor implements Runnable {

    private CalculoRepository repository;

    private MCalculo mCalculo;

    public TaskCalculoProcessor(final MCalculo mCalculo, final CalculoRepository pRepository){
        this.mCalculo = mCalculo;
        this.repository = pRepository;
    }

    @Override
    public void run() {
        try{
            
            TimeUnit.SECONDS.sleep(10);
            
            aplicarCalculo(mCalculo);
            
            mCalculo.setEstado('F');

            final StringBuilder sb = new StringBuilder()
                .append(mCalculo.getNumero1())
                .append(" ")
                .append(mCalculo.getSinal())
                .append(" ")
                .append(mCalculo.getNumero2())
                .append(" = ")
                .append(mCalculo.getResultado());

            mCalculo.setDescricao(sb.toString());

            repository.save(mCalculo);

        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

    private void aplicarCalculo(final MCalculo pMCalculo){

        if( pMCalculo.getSinal() == '+' ){
            pMCalculo.setResultado( pMCalculo.getNumero1() + pMCalculo.getNumero2() );
        }else if( pMCalculo.getSinal() == '-' ){
            pMCalculo.setResultado( pMCalculo.getNumero1() - pMCalculo.getNumero2() );
        }else if( pMCalculo.getSinal() == '*' ){
            pMCalculo.setResultado( pMCalculo.getNumero1() * pMCalculo.getNumero2() );
        }else if( pMCalculo.getSinal() == '/' ){
            pMCalculo.setResultado( pMCalculo.getNumero1() / pMCalculo.getNumero2() );
        }

    }

}
