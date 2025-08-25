package hex.backend_hexagonal_jwt.infrastructure.controller;

import hex.backend_hexagonal_jwt.domain.dto.LoginRequest;
import hex.backend_hexagonal_jwt.domain.dto.LoginResponse;
import hex.backend_hexagonal_jwt.infrastructure.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Autenticación con Spring Security
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getName(),
                            loginRequest.getPassword()
                    )
            );

            // Creamos un UserDetails básico para generar el token
            UserDetails userDetails = User
                    .withUsername(loginRequest.getName())
                    .password(loginRequest.getPassword())
                    .authorities("ROLE_USER")
                    .build();

            // Generamos token
            final String jwt = jwtUtil.generateToken(userDetails.getUsername());
            logger.info("Token generado para usuario {}", userDetails.getUsername());

            return ResponseEntity.ok(new LoginResponse(jwt));

        } catch (BadCredentialsException e) {
            logger.warn("Intento de login fallido para usuario {}", loginRequest.getName());
            return ResponseEntity.status(401).body("Usuario o contraseña incorrectos");
        }
    }
}
