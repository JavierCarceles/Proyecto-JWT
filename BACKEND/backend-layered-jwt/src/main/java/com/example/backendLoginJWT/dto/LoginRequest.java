package com.example.backendLoginJWT.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter // Genera automáticamente los métodos get para todos los campos
@Setter // Genera automáticamente los métodos set para todos los campos
@NoArgsConstructor // Genera un constructor sin parámetros
@AllArgsConstructor // Genera un constructor con todos los parámetros
public class LoginRequest {
    private String name; // Nombre de usuario que envía el cliente para autenticación
    private String password; // Contraseña del usuario que envía el cliente para autenticación
}
