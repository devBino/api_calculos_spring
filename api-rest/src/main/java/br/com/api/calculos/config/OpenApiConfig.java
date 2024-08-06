package br.com.api.calculos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI customOpenAPI(){

        Info info = new Info();

        License license = new License();

        license.name("Api Calculos");
        license.url("https://github.com/devBino/api_calculos_spring");

        info.title("Restful API Calculos");
        info.version("v1");
        info.description("Api para gerenciamentos de Calculos");
        info.termsOfService("");
        info.license(license);

        OpenAPI openAPI = new OpenAPI();

        openAPI.info(info);

        openAPI
            .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
            .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));

        return openAPI;
        
    }

}
