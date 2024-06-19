package br.com.calculo.processor.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    
    @Autowired
    private Agenda1 agenda1;

    @Autowired
    private Agenda2 agenda2;

    @Autowired
    private Agenda3 agenda3;

    public void startScheduler(){

        Thread tarefa1 = new Thread(()->agenda1.executarProcesso());
        Thread tarefa2 = new Thread(()->agenda2.executarProcesso());
        Thread tarefa3 = new Thread(()->agenda3.executarProcesso());

        tarefa1.start();
        tarefa2.start();
        tarefa3.start();

    }

}
