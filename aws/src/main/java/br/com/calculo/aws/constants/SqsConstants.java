package br.com.calculo.aws.constants;

/**
 * Provê constantes para definir valores
 * imutáveis utilizados durante consumo de filas da AWS
 */
public class SqsConstants {
    
    /**
     * Constants localstack
     */
    public static final String END_POINT_LC_STACK = "https://localhost.localstack.cloud:4566";

    /**
     * Nome da fila de calculos na AWS
     */
    public static final String QUEUE_CALCULOS = "queue-calculos";

}
