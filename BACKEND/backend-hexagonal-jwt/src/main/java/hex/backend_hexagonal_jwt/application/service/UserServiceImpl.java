package hex.backend_hexagonal_jwt.application.service;

import hex.backend_hexagonal_jwt.domain.dto.UserDTO;
import hex.backend_hexagonal_jwt.infrastructure.mapper.UserMapper;
import hex.backend_hexagonal_jwt.domain.model.User;
import hex.backend_hexagonal_jwt.domain.service.UserService;
import hex.backend_hexagonal_jwt.port.UserRepositoryPort; // <-- solo la interfaz
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepositoryPort userRepositoryPort; // <-- puerto
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepositoryPort userRepositoryPort,
                           UserMapper userMapper,
                           PasswordEncoder passwordEncoder) {
        this.userRepositoryPort = userRepositoryPort;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean existsByName(String name) {
        return userRepositoryPort.findByName(name) != null;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepositoryPort.findByEmail(email) != null;
    }

    @Override
    public UserDTO createUser(UserDTO userDto) {
        User user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User savedUser = userRepositoryPort.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDTO getUserByName(String name) {
        User user = userRepositoryPort.findByName(name);
        return userMapper.toDto(user);
    }
}
