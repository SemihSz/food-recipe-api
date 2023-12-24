package com.food.recipe.api.config;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.*;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        OpenAPI api = new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))

                .info(new Info().title("My REST API")
                        .description("Some custom description of API.")
                        .version("1.0")
                        .contact(new Contact().name("Sallo Szrajbman")
                                .email("www.baeldung.com")
                                .url("salloszraj@gmail.com"))
                        .license(new License().name("License of API")
                                .url("API license URL")));

        return api;
    }

    @Bean
    public GlobalOpenApiCustomizer customizer() {
        return openApi -> openApi.getPaths().values().stream()
                .flatMap(pathItem -> pathItem.readOperations().stream())
                .forEach(operation -> {
                    operation.addParametersItem(new HeaderParameter().name("USERNAME")
                            .description("username")
                            .in(ParameterIn.HEADER.toString())
                            .schema(new Schema().type("string"))
                            .required(true));

                    operation.addParametersItem(new HeaderParameter().name("USER_ID")
                            .description("user ID")
                            .in(ParameterIn.HEADER.toString())
                            .schema(new Schema().type("string"))
                            .required(true));
                });
    }


    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }

    private Parameter createParameter(String name, String description) {
        return new Parameter()
                .name(name)
                .in(SecurityScheme.In.HEADER.toString())
                .description(description)
                .required(true)
                .schema(new StringSchema());
    }



}
