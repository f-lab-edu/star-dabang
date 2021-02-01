package dabang.star.cafe.service;

import dabang.star.cafe.domain.Member;
import dabang.star.cafe.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberMapper memberMapper;

    @Transactional
    public void join(Member member) {
        memberMapper.save(member);
    }

    public boolean duplicatedEmail(String email) {

        Optional<String> findMemberEmail = memberMapper.findByEmail(email);

        return findMemberEmail.isPresent();
    }
}
