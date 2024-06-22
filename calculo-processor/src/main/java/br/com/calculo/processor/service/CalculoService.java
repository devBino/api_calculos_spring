package br.com.calculo.processor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.calculo.processor.model.MCalculo;
import br.com.calculo.processor.model.MCalculoHistorico;
import br.com.calculo.processor.model.ifacejpa.CalculoHistReporitory;
import br.com.calculo.processor.model.ifacejpa.CalculoRepository;
import br.com.calculo.processor.type.MensagemHistoricoType;
import br.com.calculo.processor.vo.MensagemProcessVO;

/**
 * Serviço para processar calculos 
 * de maneira individual, recuperando pelo estado 
 * 'A' - Aguardando Processamento
 */
@Service
public class CalculoService implements RegistroService {
    
    private MCalculo calculo;

    @Autowired
    @Qualifier("asyncExecutor")
    private Executor asyncExecutor;

    @Autowired
    private CalculoRepository calculoRepository;

    @Autowired
    private CalculoHistReporitory histRepository;

    private List<MensagemProcessVO> mensagens;

    @Transactional
    public void processarCalculo(){
        aplicarEtapasProcessamento();
    }

    @Async
    private void aplicarEtapasProcessamento(){

        calculo = null;
        mensagens = new ArrayList<>();

        if(!getRegistro()){
            return;
        }

        System.out.println( Thread.currentThread().getName() + " iniciou id " + calculo.getId() );

        mensagens.add(new MensagemProcessVO(MensagemHistoricoType.INFO, 
            "Calculo " + calculo.getId() + " identificado", calculo.getId()));

        prepararRegistro();
        processarRegistro();
        gerarHistoricos();
        
        System.out.println( Thread.currentThread().getName() + " finalizou id " + calculo.getId() );
        System.out.println("*************************************************************************************");

    }

    @Override
    public boolean getRegistro(){
        calculo = calculoRepository.findByEstado('A');
        return calculo != null;
    }

    @Override
    public void prepararRegistro(){
        
        calculo.setEstado('P');
        calculoRepository.save(calculo);
        mensagens.add(new MensagemProcessVO(MensagemHistoricoType.INFO, 
            "Calculo em Processamento", calculo.getId()));
            
    }

    @Override
    public void processarRegistro(){
        aplicarCalculo();
        calculoRepository.save(calculo);
        mensagens.add(new MensagemProcessVO(MensagemHistoricoType.INFO, 
            "Finalizado Processo", calculo.getId()));
    }

    private void aplicarCalculo(){

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

    private void gerarHistoricos(){

        for(MensagemProcessVO bo : mensagens){

            MCalculoHistorico hist = new MCalculoHistorico();

            hist.setDescricao(bo.getMensagem());
            hist.setTipo(bo.getCodigoTipo());
            hist.setCalculo(calculo);

            histRepository.save(hist);
            
        }

    }

}
