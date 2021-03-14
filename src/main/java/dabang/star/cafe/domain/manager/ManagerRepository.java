package dabang.star.cafe.domain.manager;

import dabang.star.cafe.api.response.manager.ManagerData;

import java.util.Optional;

public interface ManagerRepository {

    Optional<ManagerData> findManagerByNameAndPassword(String name, String password);

}
