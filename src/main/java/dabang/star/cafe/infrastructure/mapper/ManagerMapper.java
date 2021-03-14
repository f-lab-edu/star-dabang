package dabang.star.cafe.infrastructure.mapper;

import dabang.star.cafe.api.response.manager.ManagerData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface ManagerMapper {

    Optional<ManagerData> getByNameAndPassword(@Param("name") String name, @Param("password") String password);

}
