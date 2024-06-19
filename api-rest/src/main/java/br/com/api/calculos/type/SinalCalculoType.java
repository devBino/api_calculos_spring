package br.com.api.calculos.type;

import java.util.Arrays;
import java.util.Optional;

/**
 * Define os tipos de calculos suportados
 * pelos sinais das operações matemáticas
 */
public enum SinalCalculoType {
    
    ADICAO( (byte)1 ){
        @Override
        public char getSinal() {
            return '+';
        }
    },
    SUBTRACAO( (byte)2 ){
        @Override
        public char getSinal() {
            return '-';
        }
    },
    MULTIPLICACAO( (byte)3 ){
        @Override
        public char getSinal() {
            return '*';
        }
    },
    DIVISAO( (byte)4 ){
        @Override
        public char getSinal() {
            return '/';
        }
    };

    char sinal;
    byte indiceSinal;
    
    SinalCalculoType(byte indiceSinal){
        this.indiceSinal = indiceSinal;
    }

    public byte getIndiceSinal() {
        return indiceSinal;
    }

    public abstract char getSinal();

    public static SinalCalculoType fromIndice(byte indiceSinal){

        final Optional<SinalCalculoType> sinalCandidato = Arrays.stream(SinalCalculoType.values())
            .filter(s -> s.getIndiceSinal() == indiceSinal)
            .findFirst();

        if( sinalCandidato.isPresent() ){
            return sinalCandidato.get();
        }

        return null;

    }

}
