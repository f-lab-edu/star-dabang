package dabang.star.cafe.infrastructure.mapper;

import dabang.star.cafe.api.response.member.MemberData;
import dabang.star.cafe.domain.member.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberMapper {

    void insert(Member member);

    void update(Member member);

    boolean exist(String email);

    Optional<MemberData> getByEmailAndPassword(String email, String password);
}
