package dabang.star.cafe.domain.manager;

import java.util.Optional;

public interface ManagerRepository {

    Optional<ManagerData> findManagerByNameAndPassword(String name, String password);

}
