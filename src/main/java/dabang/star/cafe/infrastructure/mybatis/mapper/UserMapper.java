package dabang.star.cafe.infrastructure.mybatis.mapper;

import dabang.star.cafe.domain.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    void insert(@Param("user") User user);

    User findById(@Param("id") String id);

    User findByEmail(@Param("email") String email);

    void update(@Param("user") User user);

    boolean existsByEmail(@Param("email") String email);
}
