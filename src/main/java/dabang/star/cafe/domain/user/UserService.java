package dabang.star.cafe.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean exists(User user) {
        return userRepository.existsByEmail(user.getEmail());
    }
}
