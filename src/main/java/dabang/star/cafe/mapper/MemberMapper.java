package dabang.star.cafe.mapper;

import dabang.star.cafe.domain.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    void save(Member member);
}
