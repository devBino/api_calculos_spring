package br.com.calculo.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import br.com.calculo.processor.scheduler.Scheduler;

@SpringBootApplication
@EnableScheduling
public class ProcessorApplication implements CommandLineRunner {

	@Autowired
	private Scheduler scheduler;

	public static void main(String[] args) {
		SpringApplication.run(ProcessorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		scheduler.startScheduler();
	}

}
