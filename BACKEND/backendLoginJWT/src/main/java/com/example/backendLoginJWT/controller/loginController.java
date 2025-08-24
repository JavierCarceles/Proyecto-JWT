package com.example.backendLoginJWT.controller;

import com.example.backendLoginJWT.model.User;
import com.example.backendLoginJWT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.backendLoginJWT.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class loginController {

    private static final Logger logger = LoggerFactory.getLogger(loginController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) {
        User user = userRepository.findByName(loginUser.getName());

        if (user == null || !user.getPassword().equals(loginUser.getPassword())) {
            return ResponseEntity.status(401).body("Usuario o contraseña incorrectos");
        }

        String token = jwtUtil.generateToken(user.getName());
        logger.info("Token generado para usuario {}", user.getName());
        return ResponseEntity.ok().body("{\"token\": \"" + token + "\"}");
    }
}



