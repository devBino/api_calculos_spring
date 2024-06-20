package br.com.calculo.processor.scheduler;

import static br.com.calculo.processor.constants.ProcessoConstants.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.calculo.processor.service.CalculoService;

@Component
public class ServicoAgendado {
    
    @Autowired
    private CalculoService service;

    @Scheduled(fixedRate = INTERVALO_SERVICO)
    public void executarProcesso(){
        
        final ExecutorService exec = Executors.newFixedThreadPool(QTDE_THREADS);

        for(int i=0; i<QTDE_THREADS; i++){
            exec.submit( () -> service.processarCalculo() );
        }

    }

}
