package dabang.star.cafe.domain.manager;

import java.util.Optional;

public interface ManagerRepository {

    Optional<Manager> findManagerByName(String name);
}
