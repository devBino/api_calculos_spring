package br.com.api.calculos.type;

import java.util.Arrays;
import java.util.Optional;

/**
 * Define os tipos de calculos suportados
 * pelos sinais das operações matemáticas
 */
public enum SinalCalculoType {
    
    ADICAO("adi"){
        @Override
        public char getSinal() {
            return '+';
        }
    },
    SUBTRACAO("sub"){
        @Override
        public char getSinal() {
            return '-';
        }
    },
    MULTIPLICACAO("mul"){
        @Override
        public char getSinal() {
            return '*';
        }
    },
    DIVISAO("div"){
        @Override
        public char getSinal() {
            return '/';
        }
    };

    char sinal;
    String codigoSinal;
    
    SinalCalculoType(String codigoSinal){
        this.codigoSinal = codigoSinal;
    }

    public String getCodigoSinal() {
        return codigoSinal;
    }

    public abstract char getSinal();

    public static SinalCalculoType fromCodigo(String codigoSinal){

        final Optional<SinalCalculoType> sinalCandidato = Arrays.stream(SinalCalculoType.values())
            .filter(s -> s.getCodigoSinal().equals( codigoSinal) )
            .findFirst();

        if( sinalCandidato.isPresent() ){
            return sinalCandidato.get();
        }

        return null;

    }

}
