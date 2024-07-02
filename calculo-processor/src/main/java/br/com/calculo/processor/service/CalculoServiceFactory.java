package br.com.calculo.processor.service;

import java.util.function.BiFunction;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class CalculoServiceFactory{

    /**
     * Avalia o sinal e retorna operação para o respectivo calculo.
     * Caso um sinal não suportado seja recebido, assume a adição por padrão.
     * @param sinal
     * @return
     */
    public BiFunction<Double, Double, Double> getOperation(char sinal){

        if( sinal == '+' ){
            return getAdicao();
        }

        if( sinal == '-' ){
            return getSubtracao();
        }

        if( sinal == '*' ){
            return getMultiplicacao();
        }

        if( sinal == '/' ){
            return getDivisao();
        }

        return getAdicao();

    }

    private BiFunction<Double, Double, Double> getAdicao(){
        return (x, y) -> x + y;
    }

    private BiFunction<Double, Double, Double> getSubtracao(){
        return (x, y) -> x - y;
    }

    private BiFunction<Double, Double, Double> getMultiplicacao(){
        return (x, y) -> x * y;
    }

    private BiFunction<Double, Double, Double> getDivisao(){

        BiFunction<Double, Double, Double> div = (x, y) -> {

            Predicate<Double> yZero = pNum -> !(Math.abs(y) > 0.0); 

            if( yZero.test(y) ){
                return 0.0;
            }

            return x / y;

        };

        return div;

    }

}