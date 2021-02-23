package dabang.star.cafe.domain.user;

import dabang.star.cafe.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final EncryptService encryptService;

    public boolean exists(User user) {
        return userRepository.existsByEmail(user.getEmail());
    }

    public User loadUserByLoginParam(String loginEmail, String loginPassword) {
        Optional<User> userOptional = userRepository.findByEmail(loginEmail);
        String encryptedLoginPassword = encryptService.encrypt(loginPassword);
        if (userOptional.isEmpty()
                || !userOptional.get().getPassword().equals(encryptedLoginPassword)) {
            throw new UserNotFoundException(loginEmail);
        }

        return userOptional.get();
    }
}
