package com.example.backendLoginJWT.repository;

import com.example.backendLoginJWT.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name); // Método para buscar un usuario por su nombre. Spring Data JPA genera la consulta automáticamente
    User findByEmail(String email); // Método para buscar un usuario por su email. También generado automáticamente por Spring Data JPA
}
