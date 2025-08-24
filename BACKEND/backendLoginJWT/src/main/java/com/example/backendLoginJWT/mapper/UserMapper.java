package com.example.backendLoginJWT.mapper;

import com.example.backendLoginJWT.dto.UserDTO;
import com.example.backendLoginJWT.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") // Indica que MapStruct genere un bean de Spring para inyección automática
public interface UserMapper {

    UserDTO toDto(User user); // Convierte un objeto User (entidad) a UserDTO (objeto de transferencia de datos)

    User toEntity(UserDTO userDto); // Convierte un objeto UserDTO a User (entidad)
}
