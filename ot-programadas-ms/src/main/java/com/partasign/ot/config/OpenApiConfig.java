package com.partasign.ot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI otOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("OT Programadas API")
                        .version("1.0")
                        .description("API para órdenes de trabajo programadas"));
    }
}

