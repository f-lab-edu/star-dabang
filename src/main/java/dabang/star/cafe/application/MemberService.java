package dabang.star.cafe.application;

import dabang.star.cafe.api.exception.DuplicatedException;
import dabang.star.cafe.api.exception.MemberNotFoundException;
import dabang.star.cafe.application.data.MemberData;
import dabang.star.cafe.domain.authentication.EncryptService;
import dabang.star.cafe.domain.member.Member;
import dabang.star.cafe.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final EncryptService encryptService;

    public MemberData join(String email, String password, String nickname, String telephone, String birth) {

        checkDuplicatedEmail(email);

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

    public void checkDuplicatedEmail(String email) {

        if (memberRepository.existsByEmail(email)) {
            throw new DuplicatedException("duplicated email");
        }
    }

    public void update(Member member) {
        member.setPassword(encryptService.encrypt(member.getPassword()));
        memberRepository.save(member);
    }

    public MemberData loadById(long id) {
        return memberRepository.findById(id).orElseThrow(
                () -> new MemberNotFoundException("member not found")
        );
    }

    public void secession(long id) {
        memberRepository.deleteById(id);
    }
}
