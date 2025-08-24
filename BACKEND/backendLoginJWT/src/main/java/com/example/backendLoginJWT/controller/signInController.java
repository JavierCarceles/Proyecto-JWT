package com.example.backendLoginJWT.controller;

import com.example.backendLoginJWT.dto.UserDTO;
import com.example.backendLoginJWT.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Indica que esta clase es un controlador REST y sus métodos devolverán JSON
@RequestMapping("/users") // Prefijo de la ruta para todos los endpoints de este controlador
public class SignInController {

    @Autowired // Inyecta automáticamente la implementación de UserService
    private UserService userService;

    @PostMapping // Endpoint que maneja peticiones POST a "/users"
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDto) {
        // Verifica si ya existe un usuario con el mismo nombre
        if (userService.existsByName(userDto.getName())) {
            return ResponseEntity.badRequest().body("El nombre de usuario ya existe");
        }
        // Verifica si ya existe un usuario con el mismo email
        if (userService.existsByEmail(userDto.getEmail())) {
            return ResponseEntity.badRequest().body("El email ya está registrado");
        }
        // Crea el usuario usando el servicio y obtiene el usuario guardado
        UserDTO savedUser = userService.createUser(userDto);
        // Devuelve el usuario creado con estado HTTP 200 OK
        return ResponseEntity.ok(savedUser);
    }
}
