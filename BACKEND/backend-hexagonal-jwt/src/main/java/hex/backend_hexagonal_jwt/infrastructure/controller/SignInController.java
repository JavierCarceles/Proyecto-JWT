package hex.backend_hexagonal_jwt.infrastructure.controller;

import hex.backend_hexagonal_jwt.application.UserUseCase;
import hex.backend_hexagonal_jwt.domain.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class SignInController {

    private final UserUseCase userUseCase;

    @Autowired
    public SignInController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDto) {
        try {
            UserDTO savedUser = userUseCase.createUser(userDto);
            return ResponseEntity.ok(savedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
