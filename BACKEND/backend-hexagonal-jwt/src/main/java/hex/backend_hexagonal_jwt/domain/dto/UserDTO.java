package hex.backend_hexagonal_jwt.domain.dto;

import lombok.*;

@Data // Genera automáticamente getters, setters, toString, equals y hashCode
@NoArgsConstructor // Genera un constructor sin parámetros
@AllArgsConstructor // Genera un constructor con todos los parámetros
public class UserDTO {
    private Long iduser; // ID del usuario
    private String name; // Nombre del usuario
    private String email; // Correo electrónico del usuario
    private String password; // Contraseña del usuario
    private String location; // Ubicación del usuario (opcional)
}
