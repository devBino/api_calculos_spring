package br.com.calculo.processor.scheduler;

import static br.com.calculo.processor.constants.ProcessoConstants.*;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.calculo.processor.service.CalculoService;

/**
 * Agendador do serviço de processamento 
 * dos calculos
 */
@Component
public class ServicoAgendado {
    
    @Autowired
    private CalculoService service;

    @Autowired
    @Qualifier("asyncExecutor")
    private Executor asynExecutor;

    @Async("asyncExecutor")
    @Scheduled(fixedRate = INTERVALO_SERVICO)
    public void executarProcesso(){
        
        for(int i=0; i<QTDE_THREADS; i++){
            service.processarCalculo();
        }

    }

}
