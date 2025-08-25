package hex.backend_hexagonal_jwt.domain.service;

import hex.backend_hexagonal_jwt.domain.dto.UserDTO;

public interface UserService {

    // Comprueba si ya existe un usuario con el nombre dado
    boolean existsByName(String name);

    // Comprueba si ya existe un usuario con el email dado
    boolean existsByEmail(String email);

    // Crea un nuevo usuario a partir de un UserDTO y devuelve el usuario creado
    UserDTO createUser(UserDTO userDto);

    // Obtiene la información de un usuario por su nombre de usuario
    UserDTO getUserByName(String name);
}
