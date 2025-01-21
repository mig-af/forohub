package com.mba.forohub.infra.doc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfig {


    @Bean
    public OpenAPI customOpenAPI(){
        
        return new OpenAPI()
                        .components(new Components().addSecuritySchemes(
                                                            "tu bearer-key", 
                                                            new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                                            .scheme("bearer")
                                                            .bearerFormat("JWT")
                                                            ));
    }

}
