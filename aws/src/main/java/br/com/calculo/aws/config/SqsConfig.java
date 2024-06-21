package br.com.calculo.aws.config;

import static br.com.calculo.aws.constants.SqsConstants.*;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

/**
 * Configuração do SQS AWS
 */
@Configuration
public class SqsConfig {
    
    @Bean
    public SqsAsyncClient sqsAsyncClient(){
        return SqsAsyncClient
            .builder()
            .endpointOverride(URI.create(END_POINT_LC_STACK))
            .region(Region.SA_EAST_1)
            .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("test","test")))
            .build();
    }

}
