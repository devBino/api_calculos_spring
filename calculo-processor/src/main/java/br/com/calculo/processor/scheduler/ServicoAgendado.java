package br.com.calculo.processor.scheduler;

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

    @Scheduled(fixedRate = 10000)
    public void executarProcesso(){
        
        final ExecutorService exec = Executors.newFixedThreadPool(10);

        for(int i=0; i<10; i++){
            exec.submit( () -> service.processarCalculo() );
        }

    }

}
