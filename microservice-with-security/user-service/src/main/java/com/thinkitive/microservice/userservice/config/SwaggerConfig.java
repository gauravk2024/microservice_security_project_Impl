package com.thinkitive.microservice.userservice.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(
                new Info().title("User Store API")
                        .description("This is user api!!")
                        .version("3.0").contact(new Contact().name("User").url("http://localhost/user.com").email("user@gmail.com"))
                        .license(new License().name("Oracle").url("oracle.com"))
        ).externalDocs(new ExternalDocumentation().description("If you want to add external documentation!!"));
    }
}