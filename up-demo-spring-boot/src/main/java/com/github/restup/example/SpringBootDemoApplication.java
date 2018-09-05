package com.github.restup.example;

import com.github.restup.ResourceControllerBuilderDecorator;
import java.io.Serializable;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.restup.controller.ResourceController;
import com.github.restup.controller.model.MediaType;
import com.github.restup.example.university.Course;
import com.github.restup.example.university.Student;
import com.github.restup.example.university.University;
import com.github.restup.registry.ResourceRegistry;
import com.github.restup.repository.jpa.JpaRepository;
import com.github.restup.repository.jpa.JpaRepositoryFactory;
import com.github.restup.spring.mvc.controller.UpSpringMVCConfiguration;

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
