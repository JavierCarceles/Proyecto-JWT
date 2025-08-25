package com.example.backendLoginJWT.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

@Component // Hace que Spring gestione esta clase como un bean
public class JwtUtil {

    private final String secretKey; // Clave secreta para firmar y validar tokens
    private final long expirationMs; // Tiempo de expiración en milisegundos

    public JwtUtil(
            @Value("${jwt.secret}") String secretKey, // Inyecta la clave secreta desde properties
            @Value("${jwt.expiration}") long expirationMs) { // Inyecta la expiración desde properties
        this.secretKey = secretKey;
        this.expirationMs = expirationMs;
    }

    private Key getSigningKey() {
        // Genera la clave de firma a partir del array de bytes de la clave secreta
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(String username) {
        // Genera un JWT con el nombre de usuario como subject y la expiración configurada
        return Jwts.builder()
                .setSubject(username) // Establece el username como subject del token
                .setIssuedAt(new Date()) // Fecha de creación del token
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs)) // Expiración configurable
                .signWith(getSigningKey()) // Firma el token con la clave secreta
                .compact(); // Construye el token en formato String
    }

    public String extractUsername(String token) {
        // Extrae el nombre de usuario del token
        return getClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        try {
            // Verifica que el token no haya expirado
            return !getClaims(token).getExpiration().before(new Date());
        } catch (Exception e) {
            // Si ocurre cualquier error al parsear el token, se considera inválido
            return false;
        }
    }

    private Claims getClaims(String token) {
        // Obtiene todos los claims (información) del token JWT
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // Configura la clave para verificar la firma
                .build()
                .parseClaimsJws(token) // Parsea y valida el token
                .getBody(); // Devuelve los claims del cuerpo del token
    }
}
