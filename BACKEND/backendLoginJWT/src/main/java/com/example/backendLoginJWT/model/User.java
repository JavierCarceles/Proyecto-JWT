package com.example.backendLoginJWT.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user", uniqueConstraints = {
    @UniqueConstraint(columnNames = "name"),
    @UniqueConstraint(columnNames = "email")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private Long iduser;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    private String location;
}