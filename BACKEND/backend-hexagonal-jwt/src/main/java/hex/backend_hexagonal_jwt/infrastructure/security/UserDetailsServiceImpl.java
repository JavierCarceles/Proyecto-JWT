package hex.backend_hexagonal_jwt.infrastructure.security;

import hex.backend_hexagonal_jwt.infrastructure.repository.UserRepositoryAdapter;
import hex.backend_hexagonal_jwt.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepositoryAdapter userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepositoryAdapter userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username); // Llama al adaptador de repositorio
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con nombre: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
