package dabang.star.cafe.infrastructure.repository;

import dabang.star.cafe.application.data.MemberLogin;
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

        return memberMapper.exist(email);
    }

    @Override
    public Optional<MemberLogin> findMemberByLogin(String email, String password) {

        return memberMapper.getByEmailAndPassword(email, password);
    }
}
