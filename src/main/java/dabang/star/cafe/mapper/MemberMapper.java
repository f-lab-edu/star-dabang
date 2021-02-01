package dabang.star.cafe.mapper;

import dabang.star.cafe.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberMapper {

    void save(Member member);

    Optional<String> findByEmail(String email);
}
