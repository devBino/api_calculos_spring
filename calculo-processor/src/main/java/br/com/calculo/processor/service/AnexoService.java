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
import br.com.calculo.processor.model.MCalculoHistorico;
import br.com.calculo.processor.repository.AnexoHistRepository;
import br.com.calculo.processor.repository.AnexoRepository;
import br.com.calculo.processor.repository.CalculoHistReporitory;
import br.com.calculo.processor.repository.CalculoRepository;
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
    private AnexoHistRepository anexoHistRepository;
    
    @Autowired
    private CalculoRepository calculoRepository;
    
    @Autowired
    private CalculoHistReporitory calculoHistRepository;

    @Autowired
    private AnexoBusiness anexoBusiness;

    private List<MensagemProcessVO> mensagensAnexos, mensagensCalculos;

    @Scheduled(fixedRate = 10000)
    public void processarAnexo(){
        
        anexo = null;
        mensagensAnexos = new ArrayList<>();
        mensagensCalculos = new ArrayList<>();

        if(!getRegistro()){
            return;
        }
        
        final MensagemProcessVO mensagemVO = new MensagemProcessVO(MensagemHistoricoType.INFO, 
                	String.format("Anexo %d identificado", anexo.getId()),
                	anexo.getId());

        mensagensAnexos.add(mensagemVO);

        prepararRegistro();
        processarRegistro();
        gerarHistoricosAnexos();
        gerarHistoricosCalculos();

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
        
        final MensagemProcessVO mensagemVO = new MensagemProcessVO(MensagemHistoricoType.INFO, 
                "Anexo em Processamento", anexo.getId());
        
        mensagensAnexos.add(mensagemVO);
        
    }

    @Override
    public void processarRegistro(){
        
        final String dadosCsv = new String(anexo.getData());
        final String[] linhasCsv = dadosCsv.split("\n");

        int totalLinhas = linhasCsv[0].toLowerCase().contains("n1")
            ? linhasCsv.length - 1 : linhasCsv.length;
        
        final MensagemProcessVO mensagemVO = new MensagemProcessVO(MensagemHistoricoType.INFO, 
                String.format("Processando %d Linha(s) do anexo", totalLinhas),
                anexo.getId());

        mensagensAnexos.add(mensagemVO);
        
        int indiceLinha = 1;

        for(String lin : linhasCsv){
            
            if( lin.toLowerCase().startsWith("n1") ){
                continue;
            }
            
            String[] valoresCamposCsv = lin.split(";");

            if( valoresCamposCsv.length < 3 ){
                continue;
            }
            
            MCalculo mCalc = new MCalculo();
            
            mCalc.setNumero1(Double.valueOf(valoresCamposCsv[0]));
            mCalc.setNumero2(Double.valueOf(valoresCamposCsv[1]));
            mCalc.setSinal(valoresCamposCsv[2].charAt(0));

            anexoBusiness.aplicarCalculo(mCalc, anexo.getId());

            MCalculo calculoSalvo = calculoRepository.save(mCalc);
            
            //Históricos padrões para novo calculo importado do anexo
            mensagensCalculos.add(new MensagemProcessVO(
            		MensagemHistoricoType.INFO,
            		String.format(
            				"Calculo extraído do anexo %d linha %d.", anexo.getId(), indiceLinha), 
            		calculoSalvo.getId()));
            
            mensagensCalculos.add(new MensagemProcessVO(
            		MensagemHistoricoType.INFO,
            		String.format(
            				"Calculo [%s] bem sucedido", calculoSalvo.getDescricao()), 
            		calculoSalvo.getId()));
            
            mensagensCalculos.add(new MensagemProcessVO(MensagemHistoricoType.INFO, 
            		"Finalizado Processo", calculoSalvo.getId()));

            //Histórico para anexo
            mensagensAnexos.add(new MensagemProcessVO(MensagemHistoricoType.INFO, 
                	String.format("Calculo ID = %d Finalizado", calculoSalvo.getId()),
                anexo.getId()));
            
            indiceLinha++;

        }

        anexo.setStatus('F');
        anexoRepository.save(anexo);

        mensagensAnexos.add(new MensagemProcessVO(MensagemHistoricoType.INFO, 
            "Linhas do Anexo Processadas Com Sucesso", anexo.getId()));

    }

    private void gerarHistoricosAnexos(){

        for(MensagemProcessVO bo : mensagensAnexos){

            MAnexoHistorico hist = new MAnexoHistorico();

            hist.setDescricao(bo.getMensagem());
            hist.setTipo(bo.getCodigoTipo());
            hist.setAnexo(anexo);

            anexoHistRepository.save(hist);

        }

    }
    
    private void gerarHistoricosCalculos() {
    	
    	for(MensagemProcessVO bo : mensagensCalculos){

            MCalculoHistorico hist = new MCalculoHistorico();
            
            MCalculo calculo = new MCalculo();
            
            calculo.setId(bo.getRegistroId());

            hist.setDescricao(bo.getMensagem());
            hist.setTipo(bo.getCodigoTipo());
            hist.setCalculo(calculo);

            calculoHistRepository.save(hist);
            
        }
    	
    }

}

