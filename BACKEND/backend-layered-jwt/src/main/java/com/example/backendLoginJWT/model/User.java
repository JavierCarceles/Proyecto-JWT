package com.example.backendLoginJWT.model;

import jakarta.persistence.*;
import lombok.*;

@Entity // Marca esta clase como una entidad de JPA, se mapea a una tabla en la base de datos
@Table(name = "user", uniqueConstraints = {
    @UniqueConstraint(columnNames = "name"), // Asegura que el nombre sea único
    @UniqueConstraint(columnNames = "email") // Asegura que el email sea único
})
@Data // Genera automáticamente getters, setters, toString, equals y hashCode
@NoArgsConstructor // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los atributos
public class User {

    @Id // Marca este campo como clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // El valor se genera automáticamente (auto-increment)
    @Column(name = "iduser") // Especifica el nombre de la columna en la base de datos
    private Long iduser;

    @Column(nullable = false, unique = true) // Campo obligatorio y único
    private String name;

    @Column(nullable = false) // Campo obligatorio
    private String password;

    @Column(nullable = false, unique = true) // Campo obligatorio y único
    private String email;

    private String location; // Campo opcional, puede ser nulo
}
