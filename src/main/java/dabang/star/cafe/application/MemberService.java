package dabang.star.cafe.application;

import dabang.star.cafe.application.command.MemberUpdateCommand;
import dabang.star.cafe.application.command.SignUpCommand;
import dabang.star.cafe.application.data.MemberData;
import dabang.star.cafe.application.exception.DuplicatedException;
import dabang.star.cafe.application.exception.ResourceNotFoundException;
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

    public MemberData join(SignUpCommand signUpCommand) {

        checkDuplicatedEmail(signUpCommand.getEmail());
        String encryptedPassword = encryptService.encrypt(signUpCommand.getPassword());

        Member member = signUpCommand.toMember(encryptedPassword);
        memberRepository.save(member);

        return MemberData.from(member);
    }

    public void checkDuplicatedEmail(String email) {

        if (memberRepository.existsByEmail(email)) {
            throw new DuplicatedException("duplicated email");
        }
    }

    public void update(MemberUpdateCommand memberUpdateCommand, long memberId) {

        String encryptPassword = encryptService.encrypt(memberUpdateCommand.getPassword());

        Member member = memberUpdateCommand.toMember(memberId, encryptPassword);
        memberRepository.save(member);
    }

    public MemberData loadById(long id) {
        return memberRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("member not found")
        );
    }

    public void secession(long id) {
        memberRepository.deleteById(id);
    }
}
