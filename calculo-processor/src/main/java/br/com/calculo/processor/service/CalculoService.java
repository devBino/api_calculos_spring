package br.com.calculo.processor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.calculo.processor.model.MCalculo;
import br.com.calculo.processor.model.MCalculoHistorico;
import br.com.calculo.processor.model.ifacejpa.CalculoHistReporitory;
import br.com.calculo.processor.model.ifacejpa.CalculoRepository;
import br.com.calculo.processor.type.MensagemHistoricoType;
import br.com.calculo.processor.vo.MensagemProcessVO;

/**
 * Serviço para processar calculos 
 * de maneira individual, recuperando pelo estado 
 * 'A' - Aguardando Processamento
 */
@Service
public class CalculoService implements RegistroService {
    
    private MCalculo calculo;

    @Autowired
    private CalculoRepository calculoRepository;

    @Autowired
    private CalculoHistReporitory histRepository;

    private List<MensagemProcessVO> mensagens;

    public void processarCalculo(){
        
        calculo = null;
        mensagens = new ArrayList<>();

        if(!getRegistro()){
            return;
        }

        mensagens.add(new MensagemProcessVO(MensagemHistoricoType.INFO, 
            "Calculo " + calculo.getId() + " identificado", calculo.getId()));

        prepararRegistro();
        processarRegistro();
        gerarHistoricos();

    }

    @Override
    public boolean getRegistro(){
        calculo = calculoRepository.findByEstado('A');
        return calculo != null;

    }

    @Override
    public void prepararRegistro(){
        calculo.setEstado('P');
        calculoRepository.save(calculo);
        mensagens.add(new MensagemProcessVO(MensagemHistoricoType.INFO, 
            "Calculo em Processamento", calculo.getId()));
    }

    @Override
    public void processarRegistro(){
        aplicarCalculo(calculo);
        calculoRepository.save(calculo);
        mensagens.add(new MensagemProcessVO(MensagemHistoricoType.INFO, 
            "Finalizado Processo", calculo.getId()));
    }

    private void aplicarCalculo(final MCalculo pMCalculo){

        mensagens.add(new MensagemProcessVO(MensagemHistoricoType.INFO, 
            "Calculando parametros", calculo.getId()));

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
        
        mensagens.add(new MensagemProcessVO(MensagemHistoricoType.INFO, 
            "Calculo [" + calculo.getDescricao() + "] bem sucedido", calculo.getId()));

        pMCalculo.setEstado('F');

    }

    private void gerarHistoricos(){

        for(MensagemProcessVO bo : mensagens){

            MCalculoHistorico hist = new MCalculoHistorico();

            hist.setDescricao(bo.getMensagem());
            hist.setTipo(bo.getCodigoTipo());
            hist.setCalculo(calculo);

            histRepository.save(hist);
            
        }

    }

}
