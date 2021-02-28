package dabang.star.cafe.infrastructure.service;

import dabang.star.cafe.api.exception.MemberNotFoundException;
import dabang.star.cafe.api.response.member.MemberData;
import dabang.star.cafe.domain.login.LoginService;
import dabang.star.cafe.domain.member.EncryptService;
import dabang.star.cafe.domain.member.MemberRepository;
import dabang.star.cafe.utils.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SessionLoginService implements LoginService {

    private final HttpSession httpSession;
    private final EncryptService encryptService;
    private final MemberRepository memberRepository;

    @Override
    public MemberData login(String email, String password) {

        String encryptedPassword = encryptService.encrypt(password);

        Optional<MemberData> memberDataOptional = memberRepository.findMemberByEmailAndPassword(email, encryptedPassword);
        MemberData memberData = memberDataOptional.orElseThrow(
                () -> new MemberNotFoundException("member not found")
        );

        SessionUtils.setLoginMemberId(httpSession, memberData.getId());

        return memberData;
    }

    @Override
    public void logout() {
        SessionUtils.clearLoginMemberId(httpSession);
    }

    @Override
    public MemberData accessToMyPage(long id, String password) {

        String encryptedPassword = encryptService.encrypt(password);

        Optional<MemberData> memberDataOptional = memberRepository.findMemberByIdAndPassword(id, encryptedPassword);
        MemberData memberData = memberDataOptional.orElseThrow(
                () -> new MemberNotFoundException("memeber not found")
        );

        SessionUtils.setCurrentMemberId(httpSession, id);

        return memberData;
    }

}
