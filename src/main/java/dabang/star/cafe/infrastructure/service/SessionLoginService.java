package dabang.star.cafe.infrastructure.service;

import dabang.star.cafe.api.exception.NoAuthenticationException;
import dabang.star.cafe.api.response.member.MemberData;
import dabang.star.cafe.domain.login.LoginService;
import dabang.star.cafe.domain.member.EncryptService;
import dabang.star.cafe.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static dabang.star.cafe.utils.SessionKey.LOGIN_MEMBER_ID;

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
                () -> new NoAuthenticationException("no authenticated")
        );

        httpSession.setAttribute(LOGIN_MEMBER_ID, memberData.getId());

        return memberData;
    }

    @Override
    public void logout() {
        httpSession.removeAttribute(LOGIN_MEMBER_ID);
    }

}
