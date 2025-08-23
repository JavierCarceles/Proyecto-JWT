package com.example.backendLoginJWT.repository;

import com.example.backendLoginJWT.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    User findByEmail(String email);
}
