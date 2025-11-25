package com.partasign.recepciones.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI recepcionesOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Recepciones Repuestos API")
                        .version("1.0")
                        .description("API para registrar recepciones de repuestos"));
    }
}

