package dabang.star.cafe.domain.user;

import java.util.Optional;

public interface UserRepository {
    Long save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
