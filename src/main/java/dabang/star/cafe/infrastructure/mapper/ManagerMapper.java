package dabang.star.cafe.infrastructure.mapper;

import dabang.star.cafe.domain.manager.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface ManagerMapper {

    Optional<Manager> getByName(@Param("name") String name);

}
