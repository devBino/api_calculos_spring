package br.com.api.calculos.type;

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

        SinalCalculoType sinalCalculoType = null;

        for(SinalCalculoType sinCalc : SinalCalculoType.values()){
            if( sinCalc.getIndiceSinal() == indiceSinal ){
                sinalCalculoType = sinCalc;
                break;
            }
        }

        return sinalCalculoType;

    }

}
