package com.example.backendLoginJWT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication // Marca esta clase como aplicación Spring Boot, habilitando auto-configuración
public class BackendLoginJwtApplication {

	public static void main(String[] args) {
		// Inicia la aplicación Spring Boot
		SpringApplication.run(BackendLoginJwtApplication.class, args);
	}

	@Bean // Define un bean que se gestiona dentro del contenedor de Spring
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Configura CORS para permitir peticiones desde estos orígenes
                registry.addMapping("/**") // Aplica a todas las rutas
                        .allowedOrigins("http://127.0.0.1:3000", "http://localhost:3000") // Orígenes permitidos
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP permitidos
                        .allowedHeaders("*"); // Permite cualquier encabezado
            }
        };
    }

}
