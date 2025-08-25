package com.example.backendLoginJWT.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter // Genera automáticamente los métodos get para todos los campos
@Setter // Genera automáticamente los métodos set para todos los campos
@NoArgsConstructor // Genera un constructor sin parámetros
@AllArgsConstructor // Genera un constructor con todos los parámetros
public class LoginResponse {
    private String token; // Almacena el token JWT que se enviará al cliente tras autenticarse
}
