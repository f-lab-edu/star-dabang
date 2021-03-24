package dabang.star.cafe.domain.member;

import dabang.star.cafe.api.exception.DuplicatedException;
import dabang.star.cafe.api.exception.MemberNotFoundException;
import dabang.star.cafe.domain.login.EncryptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final EncryptService encryptService;

    @Override
    public MemberData join(String email, String password, String nickname, String telephone, String birth) {

        checkDuplicatedMemberEmail(email);

        String encryptedPassword = encryptService.encrypt(password);

        Member member = Member.builder()
                .email(email)
                .password(encryptedPassword)
                .nickname(nickname)
                .telephone(telephone)
                .birth(birth)
                .build();

        memberRepository.save(member);

        return MemberData.from(member);
    }

    @Override
    public void checkDuplicatedMemberEmail(String email) {

        if (memberRepository.isExist(email)) {
            throw new DuplicatedException("duplicated Email");
        }
    }

    @Override
    public void update(Member member) {
        member.setPassword(encryptService.encrypt(member.getPassword()));
        memberRepository.save(member);
    }

    @Override
    public MemberData loadById(long id) {
        return memberRepository.findMemberById(id).orElseThrow(
                () -> new MemberNotFoundException("member not found")
        );
    }

    @Override
    public void secession(long id) {
        memberRepository.deleteById(id);
    }
}
