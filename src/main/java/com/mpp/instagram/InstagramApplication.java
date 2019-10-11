package com.mpp.instagram;

import com.mpp.instagram.storage.StorageProperties;
import com.mpp.instagram.user.entity.UserEntity;
import io.swagger.annotations.Contact;
import net.bytebuddy.implementation.bind.annotation.Origin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableSwagger2
@CrossOrigin (origins = "http://172.19.144.224:8080")
@EnableConfigurationProperties({StorageProperties.class})
public class InstagramApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstagramApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mpp.instagram"))
                //.paths(PathSelectors.ant("/foos/*"))
                .build()
                .apiInfo(apiInfo());
    }
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Instagram REST API",
                "Some custom description of API.",
                "API TOS",
                "Terms of service",
                //new UserEntity("John Doe", "www.example.com", "myeaddress@company.com"),
                "License of API", "API license URL", Collections.emptyList().toString());
    }
        
    }
