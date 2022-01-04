package com.moon.senla.educational_website.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String SWAGGER_API_TITLE = "REST API - EDUCATIONAL WEBSITE";
    private static final String SWAGGER_API_DESCRIPTION = "EDUCATIONAL WEBSITE";
    private static final String SWAGGER_API_LICENSE = "License";
    private static final String SWAGGER_API_VERSION = "1.0";

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
            .title(SWAGGER_API_TITLE)
            .description(SWAGGER_API_DESCRIPTION)
            .license(SWAGGER_API_LICENSE)
            .version(SWAGGER_API_VERSION)
            .build();
    }

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .pathMapping("/")
            .select()
            .paths(PathSelectors.regex("/api.*"))
            .build();
    }

}
