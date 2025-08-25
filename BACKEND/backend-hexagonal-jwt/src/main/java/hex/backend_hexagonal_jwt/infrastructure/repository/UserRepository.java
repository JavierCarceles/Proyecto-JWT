package hex.backend_hexagonal_jwt.infrastructure.repository;

import hex.backend_hexagonal_jwt.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    User findByEmail(String email);
}
