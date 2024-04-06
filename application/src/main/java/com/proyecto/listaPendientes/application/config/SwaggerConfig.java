package com.proyecto.listaPendientes.application.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI documentOpenApi(){
        return  new OpenAPI().
                info(new Info().title("lista Pendientes").version("1.0.0"));
    }
}
