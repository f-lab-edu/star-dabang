package dabang.star.cafe.infrastructure.repository;

import dabang.star.cafe.api.response.member.MemberData;
import dabang.star.cafe.domain.member.Member;
import dabang.star.cafe.domain.member.MemberRepository;
import dabang.star.cafe.infrastructure.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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

    @Override
    public boolean isExist(String email) {

        return memberMapper.exists(email);
    }

    @Override
    public Optional<MemberData> findMemberByEmailAndPassword(String email, String password) {

        return memberMapper.getByEmailAndPassword(email, password);
    }

    @Override
    public Optional<MemberData> findMemberById(Long id) {
        return memberMapper.findById(id);
    }
}
