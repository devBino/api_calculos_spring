package br.com.calculo.processor.service;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.calculo.processor.bo.MensagemProcessBO;
import br.com.calculo.processor.model.MAnexo;
import br.com.calculo.processor.model.MCalculo;
import br.com.calculo.processor.model.MCalculoHistorico;
import br.com.calculo.processor.repository.AnexoRepository;
import br.com.calculo.processor.repository.CalculoHistReporitory;
import br.com.calculo.processor.repository.CalculoRepository;
import br.com.calculo.processor.type.MensagemHistoricoType;

/**
 * Serviço agendado para processar calculos 
 * através de arquivos anexos recebidos pela api
 */
@Service
public class CalculoService {
    
    private MAnexo anexo;

    @Autowired
    private AnexoRepository anexoRepository;

    @Autowired
    private CalculoRepository calculoRepository;

    @Autowired
    private CalculoHistReporitory histRepository;

    private List<MensagemProcessBO> mensagens;

    @Scheduled(fixedRate = 30000)
    public void processarCalculos(){
        
        anexo = null;
        mensagens = new ArrayList<>();

        if(!getRegistro()){
            System.out.println("Aguardando Novo Anexo " + LocalDateTime.now());
            return;
        }

        System.out.println("Novo Anexo Identificado [" + anexo.getName() + "]"  + LocalDateTime.now());
        mensagens.add(new MensagemProcessBO(MensagemHistoricoType.INFO, "Anexo identificado, iniciando processo"));

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
        mensagens.add(new MensagemProcessBO(MensagemHistoricoType.INFO, "Estado do anexo alterado para P - Em Processamento"));
    }

    private void processarCalculosAnexo(){
        
        System.out.println("Iniciando processamento de " + anexo.getName() + " " + LocalDateTime.now());

        final String dadosCsv = new String(anexo.getData());
        final String[] linhasCsv = dadosCsv.split("\n");

        mensagens.add(new MensagemProcessBO(MensagemHistoricoType.INFO, "Processando " + linhasCsv.length + " Linha(s) do anexo"));

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

            MCalculo calculoSalvo = calculoRepository.save(mCalc);

            System.out.println("Calculo Realizado => " + mCalc.getDescricao());
            System.out.println("Linha Salva com sucesso");

            mensagens.add(new MensagemProcessBO(MensagemHistoricoType.INFO, "Calculo Processado com sucesso ID = " + calculoSalvo.getId()));

        }

        anexo.setStatus('F');
        anexoRepository.save(anexo);

        mensagens.add(new MensagemProcessBO(MensagemHistoricoType.INFO, "Linhas processadas e estado do anexo alterado para F - Finalizado"));

        System.out.println("Finalizando processamento de " + anexo.getName() + " " + LocalDateTime.now());

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

    private void gerarHistoricos(){
        for(MensagemProcessBO bo : mensagens){
            MCalculoHistorico hist = new MCalculoHistorico();
        }
    }

}
