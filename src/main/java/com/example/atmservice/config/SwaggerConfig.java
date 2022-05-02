package com.example.atmservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Configuration for the document API service.
 */

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket readWriteApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).useDefaultResponseMessages(true).select()
                .apis(RequestHandlerSelectors.basePackage("com.example.atmservice.controller")).paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Lloyds Read ATM API")
                .description("API for retrieving ATMs info for a given identification from the open api: opendata-v2.2#get-atms-2.2 api.")
                .version("1.0").build();
    }
    
}
