package hex.backend_hexagonal_jwt.port;

import hex.backend_hexagonal_jwt.domain.model.User;

public interface UserRepositoryPort {

    User findByName(String name);

    User findByEmail(String email);

    User save(User user);
}
