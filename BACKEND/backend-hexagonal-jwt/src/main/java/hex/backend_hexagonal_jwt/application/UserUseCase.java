package hex.backend_hexagonal_jwt.application;

import hex.backend_hexagonal_jwt.domain.dto.UserDTO;
import hex.backend_hexagonal_jwt.domain.model.User;
import hex.backend_hexagonal_jwt.port.UserRepositoryPort;
import hex.backend_hexagonal_jwt.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserUseCase {

    private final UserRepositoryPort userRepository;
    private final UserMapper userMapper;

    public UserUseCase(UserRepositoryPort userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO createUser(UserDTO userDto) {
        if (userRepository.findByName(userDto.getName()) != null) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }
        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new RuntimeException("El email ya está registrado");
        }
        User savedUser = userRepository.save(userMapper.toEntity(userDto));
        return userMapper.toDto(savedUser);
    }

    public UserDTO findByName(String name) {
        User user = userRepository.findByName(name);
        return user == null ? null : userMapper.toDto(user);
    }
}
