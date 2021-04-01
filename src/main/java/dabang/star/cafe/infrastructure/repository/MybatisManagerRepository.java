package dabang.star.cafe.infrastructure.repository;

import dabang.star.cafe.domain.manager.Manager;
import dabang.star.cafe.domain.manager.ManagerData;
import dabang.star.cafe.domain.manager.ManagerRepository;
import dabang.star.cafe.infrastructure.mapper.ManagerMapper;
import dabang.star.cafe.utils.page.Page;
import dabang.star.cafe.utils.page.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    @Override
    public Page<ManagerData> findAll(Pagination pagination) {
        int size = pagination.getSize();
        int totalCount = managerMapper.getCountAll();
        List<ManagerData> managerDataList = managerMapper.getByPagination(size, pagination.getOffset());

        return new Page<>(managerDataList, totalCount, size, pagination.getPage());
    }

    @Override
    public List<ManagerData> findByName(String name) {
        return managerMapper.getByName(name);
    }
}
