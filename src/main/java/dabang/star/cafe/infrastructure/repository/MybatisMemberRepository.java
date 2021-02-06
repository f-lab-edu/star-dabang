package dabang.star.cafe.infrastructure.repository;

import dabang.star.cafe.domain.member.Member;
import dabang.star.cafe.domain.member.MemberRepository;
import dabang.star.cafe.infrastructure.mapper.wirte.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MybatisMemberRepository implements MemberRepository {

    private final MemberMapper memberMapper;

    @Override
    public Long save(Member member) {

        if (member.getId() == null) {
            memberMapper.insert(member);
        } else {
            memberMapper.update(member);
        }

        return member.getId();
    }
}
