package com.archilabs.pica.restwrapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class RestWrapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestWrapperApplication.class, args);
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.archilabs.pica.restwrapper"))
                .build()
                .genericModelSubstitutes(ResponseEntity.class)
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Pica Integrations - Soap Wrappers",
                "Soap wrappers for communication with ledacy and third party systems",
                "1.0.0",
                "",
                new Contact("Miguel Arroyo", "https://github.com/dartharrmi/pica-external-integrations", "ma_arroyos@javeriana.edu.co"),
                "",
                "",
                Collections.emptyList()
        );
    }
}
