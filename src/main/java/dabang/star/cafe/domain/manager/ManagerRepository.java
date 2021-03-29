package dabang.star.cafe.domain.manager;

import java.util.Optional;

public interface ManagerRepository {

    Long save(Manager manager);

    Optional<ManagerData> findByNameAndPassword(String name, String password);

    Optional<Manager> findById(long id);
}
