package br.com.calculo.processor.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.calculo.processor.service.CalculoService;

@Component
public class Agenda2 {
    
    @Autowired
    private CalculoService service;

    @Scheduled(fixedRate = 10000)
    public void executarProcesso(){
        service.processarCalculo();
    }

}
