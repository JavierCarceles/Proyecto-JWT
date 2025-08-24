package com.example.backendLoginJWT.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration // Marca esta clase como configuración de Spring
@EnableWebSecurity // Habilita la seguridad web con Spring Security
public class SecurityConfig {

    @Autowired // Inyección del filtro JWT personalizado
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults()) // Habilita CORS con configuración por defecto
            .csrf(AbstractHttpConfigurer::disable) // Desactiva CSRF para APIs REST
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
                // Política de sesión sin estado: no se mantiene sesión en el servidor, cada petición debe enviar el token JWT
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/login",             // Ruta de login permitida sin autenticación
                    "/users",             // Registro de usuarios permitido sin autenticación
                    "/v3/api-docs/**",    // Rutas de documentación de Swagger permitidas
                    "/swagger-ui/**",     // Interfaz Swagger UI permitida
                    "/swagger-ui.html"    // Archivo HTML de Swagger permitido
                ).permitAll() // Todas las demás rutas requieren autenticación
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); 
            // Añade el filtro JWT antes del filtro de autenticación estándar de Spring Security

        return http.build(); // Construye y devuelve la cadena de filtros de seguridad
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); 
        // Bean para codificar contraseñas con BCrypt (hash seguro)
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager(); 
        // Bean que expone el AuthenticationManager para autenticar usuarios con Spring Security
    }
}
