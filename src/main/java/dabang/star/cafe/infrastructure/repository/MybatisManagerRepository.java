package dabang.star.cafe.infrastructure.repository;

import dabang.star.cafe.api.response.manager.ManagerData;
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
    public Optional<ManagerData> findManagerByNameAndPassword(String name, String password) {
        return managerMapper.getByNameAndPassword(name, password);
    }

}
