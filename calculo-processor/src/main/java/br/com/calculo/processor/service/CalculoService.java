package br.com.calculo.processor.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.calculo.processor.model.MAnexo;
import br.com.calculo.processor.model.MCalculo;
import br.com.calculo.processor.repository.AnexoRepository;
import br.com.calculo.processor.repository.CalculoRepository;

@Service
public class CalculoService {
    
    private MAnexo anexo;

    @Autowired
    private AnexoRepository anexoRepository;

    @Autowired
    private CalculoRepository calculoRepository;

    @Scheduled(fixedRate = 15000)
    public void processarCalculos(){
        
        anexo = null;

        if(!getRegistro()){
            System.out.println("Aguardando Novo Anexo " + LocalDateTime.now());
            return;
        }

        System.out.println("Novo Anexo Identificado [" + anexo.getName() + "]"  + LocalDateTime.now());
        
        prepararCalculo();

        processarCalculosAnexo();

    }

    private boolean getRegistro(){
        
        anexo = anexoRepository.getLastFileWaitingProcess('A');

        return anexo != null;

    }

    private void prepararCalculo(){
        anexo.setStatus('P');
        anexoRepository.save(anexo);
        System.out.println("Calculo Estado mudado para \"P\"");
    }

    private void processarCalculosAnexo(){
        
        System.out.println("Iniciando processamento de " + anexo.getName() + " " + LocalDateTime.now());

        final String dadosCsv = new String(anexo.getData());
        final String[] linhasCsv = dadosCsv.split("\n");

        for(String lin : linhasCsv){
            
            if( lin.toLowerCase().startsWith("n1") ){
                continue;
            }
            
            System.out.println("Processando Linha " + lin);

            String[] valoresCamposCsv = lin.split(";");

            MCalculo mCalc = new MCalculo();
            
            mCalc.setNumero1(Double.valueOf(valoresCamposCsv[0]));
            mCalc.setNumero2(Double.valueOf(valoresCamposCsv[1]));
            mCalc.setSinal(valoresCamposCsv[2].charAt(0));

            aplicarCalculo(mCalc);

            calculoRepository.save(mCalc);

            System.out.println("Calculo Realizado => " + mCalc.getDescricao());
            System.out.println("Linha Salva com sucesso");

        }

        System.out.println("Iniciando processamento de " + anexo.getName() + " " + LocalDateTime.now());

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

        final StringBuilder sb = new StringBuilder()
                .append(pMCalculo.getNumero1())
                .append(" ")
                .append(pMCalculo.getSinal())
                .append(" ")
                .append(pMCalculo.getNumero2())
                .append(" = ")
                .append(pMCalculo.getResultado());

                
        pMCalculo.setDescricao(sb.toString());
        
        pMCalculo.setEstado('F');

    }

}
