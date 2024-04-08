package br.com.calculadora.cache;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.calculadora.bo.v1.CalculoBO;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CalculoCache {
    
    private List<CalculoBO> calculos = new ArrayList<>();

    public List<CalculoBO> getCalculos() {
        return calculos;
    }

}
