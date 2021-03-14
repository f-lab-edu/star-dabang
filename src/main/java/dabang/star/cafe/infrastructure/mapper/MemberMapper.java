package dabang.star.cafe.infrastructure.mapper;

import dabang.star.cafe.domain.member.MemberData;
import dabang.star.cafe.domain.member.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface MemberMapper {

    void insert(Member member);

    void update(Member member);

    boolean exists(@Param("email") String email);

    Optional<MemberData> getByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    Optional<MemberData> getById(@Param("id") Long id);

    Optional<MemberData> getByIdAndPassword(@Param("id") Long id, @Param("password") String password);

    void removeById(@Param("id") Long id);
}
