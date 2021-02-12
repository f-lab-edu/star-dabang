package dabang.star.cafe.infrastructure.mapper.read;

import dabang.star.cafe.application.data.MemberLogin;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberReadService {

    boolean exist(String email);

    Optional<MemberLogin> getMemberByLogin(String email, String password);
}
