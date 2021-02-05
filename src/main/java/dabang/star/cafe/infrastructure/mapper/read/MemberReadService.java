package dabang.star.cafe.infrastructure.mapper.read;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberReadService {

    boolean exist(String email);
}
