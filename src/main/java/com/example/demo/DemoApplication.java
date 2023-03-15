package com.example.demo;

import com.example.demo.configuration.FileStorageProperties;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
@OpenAPIDefinition(info = @Info(title = "EliSphere API",
                                version = "1.0",
                                description = "Api per il socialnetwork EliSphere",
                                termsOfService = "elis.org"))
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
