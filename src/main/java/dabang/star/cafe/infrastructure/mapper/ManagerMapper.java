package dabang.star.cafe.infrastructure.mapper;

import dabang.star.cafe.domain.manager.Manager;
import dabang.star.cafe.domain.manager.ManagerData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface ManagerMapper {

    void insert(Manager manager);

    void update(Manager manager);

    Optional<ManagerData> getByNameAndPassword(@Param("name") String name, @Param("password") String password);

    Optional<Manager> getById(Long id);

    void removeById(Long id);
}
