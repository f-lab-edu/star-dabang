package dabang.star.cafe.domain.manager;

import dabang.star.cafe.application.data.ManagerData;
import dabang.star.cafe.utils.page.Page;
import dabang.star.cafe.utils.page.Pagination;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository {

    Long save(Manager manager);

    Optional<ManagerData> findByNameAndPassword(String name, String password);

    Optional<Manager> findById(long id);

    void deleteById(long id);

    Page<ManagerData> findAll(Pagination pagination);

    List<ManagerData> findByName(String name);

    Optional<String> findTokenById(long id);

}
