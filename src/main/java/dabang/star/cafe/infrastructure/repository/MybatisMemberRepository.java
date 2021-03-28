package dabang.star.cafe.infrastructure.repository;

import dabang.star.cafe.domain.member.MemberData;
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
    public void save(Member member) {

        if (member.getId() == null) {
            memberMapper.insert(member);
        } else {
            memberMapper.update(member);
        }

    }

    @Override
    public boolean existsByEmail(String email) {
        return memberMapper.exists(email);
    }

    @Override
    public Optional<MemberData> findByEmailAndPassword(String email, String password) {
        return memberMapper.getByEmailAndPassword(email, password);
    }

    @Override
    public Optional<MemberData> findById(long id) {
        return memberMapper.getById(id);
    }

    @Override
    public Optional<MemberData> findByIdAndPassword(long id, String password) {
        return memberMapper.getByIdAndPassword(id, password);
    }

    @Override
    public void deleteById(long id) {
        memberMapper.removeById(id);
    }
}
