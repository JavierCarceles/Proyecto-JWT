package com.example.backendLoginJWT.controller;

import com.example.backendLoginJWT.dto.LoginRequest;
import com.example.backendLoginJWT.dto.LoginResponse;
import com.example.backendLoginJWT.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController // Indica que esta clase es un controlador REST y sus métodos devolverán JSON
@RequestMapping // No se especifica prefijo de ruta aquí
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier origen (CORS)
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired // Inyecta el AuthenticationManager de Spring Security
    private AuthenticationManager authenticationManager;

    @Autowired // Inyecta el UserDetailsService para obtener información del usuario
    private UserDetailsService userDetailsService;

    @Autowired // Inyecta la utilidad de JWT para generar y validar tokens
    private JwtUtil jwtUtil;

    @PostMapping("/login") // Endpoint que maneja peticiones POST a "/login"
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Intenta autenticar al usuario con el nombre y la contraseña proporcionados
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getName(),
                            loginRequest.getPassword()
                    )
            );

            // Si la autenticación es exitosa, se obtiene la información completa del usuario
            final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getName());
            
            // Genera un token JWT para el usuario
            final String jwt = jwtUtil.generateToken(userDetails.getUsername());

            // Registra en consola que se generó el token
            logger.info("Token generado para usuario {}", userDetails.getUsername());

            // Devuelve el token en la respuesta con estado HTTP 200 OK
            return ResponseEntity.ok(new LoginResponse(jwt));
        } catch (BadCredentialsException e) {
            // Si la autenticación falla, registra un warning y devuelve un 401
            logger.warn("Intento de login fallido para usuario {}", loginRequest.getName());
            return ResponseEntity.status(401).body("Usuario o contraseña incorrectos");
        }
    }
}
