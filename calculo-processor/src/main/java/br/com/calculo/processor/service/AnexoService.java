package br.com.calculo.processor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.calculo.processor.bo.MensagemProcessBO;
import br.com.calculo.processor.model.MAnexo;
import br.com.calculo.processor.model.MAnexoHistorico;
import br.com.calculo.processor.model.MCalculo;
import br.com.calculo.processor.repository.AnexoHistRepository;
import br.com.calculo.processor.repository.AnexoRepository;
import br.com.calculo.processor.repository.CalculoRepository;
import br.com.calculo.processor.type.MensagemHistoricoType;

/**
 * Serviço agendado para processar calculos 
 * através de arquivos anexos recebidos pela api
 */
@Service
public class AnexoService implements RegistroService {
    
    private MAnexo anexo;

    @Autowired
    private AnexoRepository anexoRepository;

    @Autowired
    private AnexoHistRepository histRepository;

    @Autowired
    private CalculoRepository calculoRepository;

    private List<MensagemProcessBO> mensagens;

    @Scheduled(fixedRate = 30000)
    public void processarAnexo(){
        
        anexo = null;
        mensagens = new ArrayList<>();

        if(!getRegistro()){
            return;
        }

        mensagens.add(new MensagemProcessBO(MensagemHistoricoType.INFO, 
            "Anexo " + anexo.getId() + " identificado", anexo.getId()));

        prepararRegistro();
        processarRegistro();
        gerarHistoricos();

    }

    @Override
    public boolean getRegistro() {
        anexo = anexoRepository.getLastFileWaitingProcess('A');
        return anexo != null;
    }

    @Override
    public void prepararRegistro(){
        anexo.setStatus('P');
        anexoRepository.save(anexo);
        mensagens.add(new MensagemProcessBO(MensagemHistoricoType.INFO, 
            "Anexo em Processamento", anexo.getId()));
    }

    @Override
    public void processarRegistro(){
        
        final String dadosCsv = new String(anexo.getData());
        final String[] linhasCsv = dadosCsv.split("\n");

        int totalLinhas = linhasCsv[0].toLowerCase().contains("n1")
            ? linhasCsv.length - 1 : linhasCsv.length;

        mensagens.add(new MensagemProcessBO(MensagemHistoricoType.INFO, 
            "Processando " + totalLinhas + " Linha(s) do anexo", anexo.getId()));

        for(String lin : linhasCsv){
            
            if( lin.toLowerCase().startsWith("n1") ){
                continue;
            }
            
            String[] valoresCamposCsv = lin.split(";");

            MCalculo mCalc = new MCalculo();
            
            mCalc.setNumero1(Double.valueOf(valoresCamposCsv[0]));
            mCalc.setNumero2(Double.valueOf(valoresCamposCsv[1]));
            mCalc.setSinal(valoresCamposCsv[2].charAt(0));

            aplicarCalculo(mCalc);

            MCalculo calculoSalvo = calculoRepository.save(mCalc);

            mensagens.add(new MensagemProcessBO(MensagemHistoricoType.INFO, 
                "Calculo ID = " + calculoSalvo.getId() + " Finalizado", anexo.getId()));

        }

        anexo.setStatus('F');
        anexoRepository.save(anexo);

        mensagens.add(new MensagemProcessBO(MensagemHistoricoType.INFO, 
            "Linhas do Anexo Processadas Com Sucesso", anexo.getId()));

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

            MAnexoHistorico hist = new MAnexoHistorico();

            hist.setDescricao(bo.getMensagem());
            hist.setTipo(bo.getCodigoTipo());
            hist.setAnexo(anexo);

            histRepository.save(hist);

        }

    }

}

