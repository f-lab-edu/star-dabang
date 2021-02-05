package dabang.star.cafe.infrastructure.mapper.wirte;

import dabang.star.cafe.domain.member.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    void insert(Member member);

    void update(Member member);
}
