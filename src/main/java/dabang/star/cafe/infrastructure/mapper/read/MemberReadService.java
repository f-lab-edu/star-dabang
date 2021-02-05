package dabang.star.cafe.infrastructure.mapper.read;

import dabang.star.cafe.domain.member.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberReadService {

    boolean exist(String email);

    Optional<Member> findByEmail(String email);

    Optional<Long> findByEmailAndPassword(String email, String password);
}
