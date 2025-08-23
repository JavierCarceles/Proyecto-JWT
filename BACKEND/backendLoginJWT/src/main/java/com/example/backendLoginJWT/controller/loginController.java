package com.example.backendLoginJWT.controller;

import com.example.backendLoginJWT.model.User;
import com.example.backendLoginJWT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping  // Opcional, puedes dejar vacío o poner un prefijo si quieres
@CrossOrigin(origins = "*")
public class loginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) {
        User user = userRepository.findByName(loginUser.getName());

        if (user == null || !user.getPassword().equals(loginUser.getPassword())) {
            return ResponseEntity.status(401).body("Usuario o contraseña incorrectos");
        }
        return ResponseEntity.ok("Login correcto");
    }
}


