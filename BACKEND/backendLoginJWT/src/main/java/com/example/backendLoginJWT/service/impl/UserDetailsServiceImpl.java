package com.example.backendLoginJWT.service.impl;

import com.example.backendLoginJWT.model.User;
import com.example.backendLoginJWT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service // Marca la clase como un servicio de Spring, se registra automáticamente como bean
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // Inyección del repositorio para acceder a los usuarios en la base de datos

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username); // Busca el usuario por su nombre en la base de datos
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con nombre: " + username);  // Lanza excepción si el usuario no existe
        }

        return new org.springframework.security.core.userdetails.User(
                user.getName(), // Nombre de usuario que usará Spring Security
                user.getPassword(), // Contraseña cifrada
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")) 
                // Asigna el rol "ROLE_USER" al usuario para la autorización
        );
    }
}
