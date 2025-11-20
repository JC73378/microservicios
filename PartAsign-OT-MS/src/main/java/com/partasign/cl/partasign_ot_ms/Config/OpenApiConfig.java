package com.partasign.cl.partasign_ot_ms.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI otApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("PartAsign OT Programadas API")
                        .version("1.0")
                        .description("API para gestión de OT programadas"));
    }
}
