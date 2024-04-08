package br.com.calculadora.converter.v1;

import br.com.calculadora.bo.v1.CalculoBO;
import br.com.calculadora.model.MCalculo;

public class CalculoConverter {
    
    public static MCalculo toModel(final CalculoBO bo){
        
        MCalculo md = new MCalculo();
        
        md.setId(bo.getId());
        md.setNumero1(bo.getNumero1());
        md.setNumero2(bo.getNumero2());
        md.setResultado(bo.getResultado());
        md.setSinal(bo.getSinal());
        md.setEstado(bo.getEstado());

        return md;

    }

    public static CalculoBO toBO(final MCalculo md){
        
        CalculoBO bo = new CalculoBO();
        
        bo.setId(md.getId());
        bo.setNumero1(md.getNumero1());
        bo.setNumero2(md.getNumero2());
        bo.setResultado(md.getResultado());
        bo.setSinal(md.getSinal());
        bo.setEstado(md.getEstado());

        return bo;

    }

}
