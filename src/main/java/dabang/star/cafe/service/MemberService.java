package dabang.star.cafe.service;

import dabang.star.cafe.domain.Member;
import dabang.star.cafe.exception.DuplicatedException;
import dabang.star.cafe.mapper.MemberMapper;
import dabang.star.cafe.utils.EncryptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberMapper memberMapper;

    @Transactional
    public void join(Member member) {
        member.setPassword(EncryptionUtil.encrypt(member.getPassword()));

        memberMapper.save(member);
    }

    public boolean duplicatedEmail(String email) {

        Optional<String> findMemberEmail = memberMapper.findByEmail(email);

        if (findMemberEmail.isPresent()) {
            throw new DuplicatedException("duplicated Email");
        }

        return findMemberEmail.isPresent();
    }
}
