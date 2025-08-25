package hex.backend_hexagonal_jwt.infrastructure.mapper;

import hex.backend_hexagonal_jwt.domain.dto.UserDTO;
import hex.backend_hexagonal_jwt.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") // Indica que MapStruct genere un bean de Spring para inyección automática
public interface UserMapper {

    UserDTO toDto(User user); // Convierte un objeto User (entidad) a UserDTO (objeto de transferencia de datos)

    User toEntity(UserDTO userDto); // Convierte un objeto UserDTO a User (entidad)
}
