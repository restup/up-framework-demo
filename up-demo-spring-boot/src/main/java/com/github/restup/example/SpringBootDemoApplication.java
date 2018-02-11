package com.github.restup.example;

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
import com.github.restup.registry.ResourceRegistry;
import com.github.restup.repository.jpa.JpaRepository;
import com.github.restup.repository.jpa.JpaRepositoryFactory;
import com.github.restup.spring.mvc.controller.UpSpringMVCConfiguration;
import com.university.Course;
import com.university.Student;
import com.university.University;

@SpringBootApplication
@EntityScan(basePackages = {"com.university"})
@Import(UpSpringMVCConfiguration.class)
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

    @Autowired
    @Bean
    public JpaRepository<?, ?> jpaRepository(EntityManager entityManager) {
        // JpaRepository has to be defined as a spring bean to be proxied for transaction management
        return new JpaRepository<Object, Serializable>(entityManager);
    }

    @Autowired
    @Bean
    public ResourceRegistry registry(JpaRepository<?, ?> jpaRepository) {

        // build registry setting, minimally passing in a repository factory
        ResourceRegistry registry = ResourceRegistry.builder()
                .repositoryFactory(new JpaRepositoryFactory(jpaRepository)).build();

        // register university classes with defaults
        registry.registerResource(Course.class
                , Student.class
                , University.class);

        return registry;
    }

    @Bean
    @Autowired
    public ResourceController resourceController(ResourceRegistry registry, ObjectMapper mapper) {
        // create new resource controller 
        // a Spring MVC Controller is configured by UpSpringMVCConfiguration imported above
        return ResourceController.builder()
                .jacksonObjectMapper(mapper)
                .registry(registry)
                .defaultMediaType(MediaType.APPLICATION_JSON_API)
                .build();
    }

}
