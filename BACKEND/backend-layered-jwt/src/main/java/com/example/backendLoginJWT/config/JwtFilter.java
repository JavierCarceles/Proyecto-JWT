package com.example.backendLoginJWT.config;

import com.example.backendLoginJWT.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    // Inyectamos el componente JwtUtil para generar y validar tokens
    @Autowired
    private JwtUtil jwtUtil;

    // Inyectamos el UserDetailsService para cargar datos del usuario
    @Autowired
    private UserDetailsService userDetailsService;

    // Lista de rutas que no requieren autenticación
    private static final List<String> EXCLUDED_PATHS = List.of("/login", "/users");

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Obtenemos la ruta actual de la petición
        String path = request.getServletPath();

        // Si la ruta está en la lista de rutas excluidas, dejamos pasar la petición
        if (EXCLUDED_PATHS.stream().anyMatch(path::startsWith)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Obtenemos el header Authorization
        final String authHeader = request.getHeader("Authorization");
        String jwt = null;
        String username = null;

        // Si el header empieza con "Bearer ", extraemos el token
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            try {
                // Extraemos el username del token
                username = jwtUtil.extractUsername(jwt);
            } catch (Exception e) {
                // Si el token es inválido o expirado, respondemos con 401
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token inválido o expirado.");
                return;
            }
        }

        // Si tenemos un username válido y no hay autenticación en el contexto de seguridad
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Validamos el token
            if (jwtUtil.validateToken(jwt)) {
                // Cargamos los detalles del usuario
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                // Creamos un token de autenticación con los datos del usuario
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                // Asociamos los detalles de la petición al token de autenticación
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // Guardamos la autenticación en el contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continuamos con el siguiente filtro de la cadena
        filterChain.doFilter(request, response);
    }
}
