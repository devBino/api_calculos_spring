package br.com.calculo.processor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.calculo.processor.business.AnexoBusiness;
import br.com.calculo.processor.model.MAnexo;
import br.com.calculo.processor.model.MAnexoHistorico;
import br.com.calculo.processor.model.MCalculo;
import br.com.calculo.processor.model.ifacejpa.AnexoHistRepository;
import br.com.calculo.processor.model.ifacejpa.AnexoRepository;
import br.com.calculo.processor.model.ifacejpa.CalculoRepository;
import br.com.calculo.processor.type.MensagemHistoricoType;
import br.com.calculo.processor.vo.MensagemProcessVO;

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

    @Autowired
    private AnexoBusiness anexoBusiness;

    private List<MensagemProcessVO> mensagens;

    @Scheduled(fixedRate = 10000)
    public void processarAnexo(){
        
        anexo = null;
        mensagens = new ArrayList<>();

        if(!getRegistro()){
            return;
        }

        mensagens.add(new MensagemProcessVO(MensagemHistoricoType.INFO, 
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
        mensagens.add(new MensagemProcessVO(MensagemHistoricoType.INFO, 
            "Anexo em Processamento", anexo.getId()));
    }

    @Override
    public void processarRegistro(){
        
        final String dadosCsv = new String(anexo.getData());
        final String[] linhasCsv = dadosCsv.split("\n");

        int totalLinhas = linhasCsv[0].toLowerCase().contains("n1")
            ? linhasCsv.length - 1 : linhasCsv.length;

        mensagens.add(new MensagemProcessVO(MensagemHistoricoType.INFO, 
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

            anexoBusiness.aplicarCalculo(mCalc, anexo.getId());

            MCalculo calculoSalvo = calculoRepository.save(mCalc);

            mensagens.add(new MensagemProcessVO(MensagemHistoricoType.INFO, 
                "Calculo ID = " + calculoSalvo.getId() + " Finalizado", anexo.getId()));

        }

        anexo.setStatus('F');
        anexoRepository.save(anexo);

        mensagens.add(new MensagemProcessVO(MensagemHistoricoType.INFO, 
            "Linhas do Anexo Processadas Com Sucesso", anexo.getId()));

    }

    private void gerarHistoricos(){

        for(MensagemProcessVO bo : mensagens){

            MAnexoHistorico hist = new MAnexoHistorico();

            hist.setDescricao(bo.getMensagem());
            hist.setTipo(bo.getCodigoTipo());
            hist.setAnexo(anexo);

            histRepository.save(hist);

        }

    }

}

