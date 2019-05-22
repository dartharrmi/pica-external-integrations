package com.archilabs.pica.bolivariano;

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
public class BolivarianoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BolivarianoApplication.class, args);
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.archilabs.pica.bolivariano"))
                .build()
                .genericModelSubstitutes(ResponseEntity.class)
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Pica Integrations - Bolivariano",
                "Legacy wrappers for communication with Bolivariano ftp services.",
                "1.0.0",
                "",
                new Contact("Miguel Arroyo", "https://github.com/dartharrmi/pica-external-integrations", "ma_arroyos@javeriana.edu.co"),
                "",
                "",
                Collections.emptyList()
        );
    }
}
