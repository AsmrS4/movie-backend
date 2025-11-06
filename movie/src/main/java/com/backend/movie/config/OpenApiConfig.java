package com.backend.movie.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        name = "BearerAuth")
public class OpenApiConfig {
    @Bean
    public OpenAPI defineOpenAPI () {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("B0nhart");
        myContact.setEmail("b0nhart@example.com");

        Info info = new Info()
                .title("Системное API для каталога кинофильмов")
                .version("1.0")
                .description("Это API предоставляет эндпоинты для каталога фильмов.")
                .contact(myContact);
        return new OpenAPI().info(info).servers(List.of(server));
    }
}
