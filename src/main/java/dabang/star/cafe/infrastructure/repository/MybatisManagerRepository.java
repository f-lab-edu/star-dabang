package dabang.star.cafe.infrastructure.repository;

import dabang.star.cafe.domain.manager.Manager;
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
    public Optional<Manager> findManagerByName(String name) {
        return managerMapper.getByName(name);
    }
}
