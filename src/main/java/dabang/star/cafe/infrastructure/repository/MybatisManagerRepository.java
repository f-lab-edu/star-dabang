package dabang.star.cafe.infrastructure.repository;

import dabang.star.cafe.domain.manager.Manager;
import dabang.star.cafe.domain.manager.ManagerData;
import dabang.star.cafe.domain.manager.ManagerRepository;
import dabang.star.cafe.infrastructure.mapper.ManagerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MybatisManagerRepository implements ManagerRepository {

    private final ManagerMapper managerMapper;

    @Override
    public Long save(Manager manager) {

        if (manager.getId() == null) {
            managerMapper.insert(manager);
        } else {
            managerMapper.update(manager);
        }

        return manager.getId();
    }

    @Override
    public Optional<ManagerData> findByNameAndPassword(String name, String password) {
        return managerMapper.getByNameAndPassword(name, password);
    }

    @Override
    public Optional<Manager> findById(long id) {
        return managerMapper.getById(id);
    }

    @Override
    public void deleteById(long id) {
        managerMapper.removeById(id);
    }
}
