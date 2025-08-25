package com.example.backendLoginJWT.service.impl;

import com.example.backendLoginJWT.dto.UserDTO;
import com.example.backendLoginJWT.mapper.UserMapper;
import com.example.backendLoginJWT.model.User;
import com.example.backendLoginJWT.repository.UserRepository;
import com.example.backendLoginJWT.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service // Marca la clase como un servicio de Spring, se registra automáticamente como bean
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository; // Inyección del repositorio para acceder a la base de datos

    @Autowired
    private UserMapper userMapper; // Inyección del mapper para convertir entre User y UserDTO

    @Autowired
    private PasswordEncoder passwordEncoder; // Inyección del encoder para cifrar contraseñas

    @Override
    public boolean existsByName(String name) {
        return userRepository.findByName(name) != null; // Devuelve true si encuentra un usuario con ese nombre
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email) != null; // Devuelve true si encuentra un usuario con ese email
    }

    @Override
    public UserDTO createUser(UserDTO userDto) {
        User user = userMapper.toEntity(userDto); // Convierte el DTO a entidad User
        user.setPassword(passwordEncoder.encode(userDto.getPassword())); // Cifra la contraseña
        User savedUser = userRepository.save(user); // Guarda el usuario en la base de datos
        return userMapper.toDto(savedUser); // Convierte la entidad guardada de vuelta a DTO y la devuelve
    }

    @Override
    public UserDTO getUserByName(String name) {
        User user = userRepository.findByName(name); // Busca el usuario por nombre
        return userMapper.toDto(user); // Convierte a DTO y lo devuelve
    }
}
