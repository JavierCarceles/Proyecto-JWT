package com.example.backendLoginJWT.controller;

import com.example.backendLoginJWT.model.User;
import com.example.backendLoginJWT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class signInController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (userRepository.findByName(user.getName()) != null) {
            return ResponseEntity.badRequest().body("El nombre de usuario ya existe");
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("El email ya está registrado");
        }
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }
}