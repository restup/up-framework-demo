package com.github.restup.example;

import com.github.restup.controller.ResourceControllerBuilderDecorator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EntityScan
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

    @Bean
    public ResourceControllerBuilderDecorator resourceControllerBuilderDecorator() {
        return (b) -> b
//            .defaultMediaType(MediaType.APPLICATION_JSON_API)
            .mediaTypeParam("mediaType");
    }

}
