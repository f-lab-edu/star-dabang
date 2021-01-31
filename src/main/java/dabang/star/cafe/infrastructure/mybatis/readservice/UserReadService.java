package dabang.star.cafe.infrastructure.mybatis.readservice;

import dabang.star.cafe.application.data.UserData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserReadService {
    UserData findById(@Param("id") String id);

    UserData findByEmail(@Param("email") String email);
}
