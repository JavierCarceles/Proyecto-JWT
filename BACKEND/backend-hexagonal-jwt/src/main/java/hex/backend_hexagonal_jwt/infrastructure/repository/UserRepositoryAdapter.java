package hex.backend_hexagonal_jwt.infrastructure.repository;

import hex.backend_hexagonal_jwt.domain.model.User;
import hex.backend_hexagonal_jwt.port.UserRepositoryPort;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserRepository userRepository; // tu JPA Repository

    @Autowired
    public UserRepositoryAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
