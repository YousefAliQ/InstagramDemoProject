package com.mpp.instagram;

import com.mpp.instagram.storage.StorageProperties;
import com.mpp.instagram.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Collections;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableConfigurationProperties({StorageProperties.class})
@EnableSwagger2
@CrossOrigin (origins = "http://localhost:3000", maxAge = 3600)
public class InstagramApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstagramApplication.class, args);
    }

//    public WebMvcConfigurer corsConfigurer() {
////        return new WebMvcConfigurer() {
////            @Override
////            public void addCorsMappings(CorsRegistry registry) {
////                registry.addMapping("/signin").allowedOrigins("http://172.19.144.224:3000");
////            }
////        };
////    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            //storageService.deleteAll();
            storageService.init();
        };
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
                "First project of Modern Programming Practise at MUM.",
                "API TOS",
                "Terms of service",
                //new UserEntity("John Doe", "www.example.com", "myeaddress@company.com"),
                "License of API", "API license URL", Collections.emptyList().toString());
    }

}


