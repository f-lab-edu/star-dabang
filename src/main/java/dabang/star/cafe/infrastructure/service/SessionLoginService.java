package dabang.star.cafe.infrastructure.service;

import dabang.star.cafe.api.exception.NotAuthenticationException;
import dabang.star.cafe.application.data.MemberLogin;
import dabang.star.cafe.application.data.MemberNickname;
import dabang.star.cafe.domain.member.EncryptService;
import dabang.star.cafe.domain.login.LoginService;
import dabang.star.cafe.domain.member.Member;
import dabang.star.cafe.domain.member.MemberRepository;
import dabang.star.cafe.utils.SessionKey;
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
    public MemberNickname login(String email, String password) {

        String requestEmail = email;
        String requestPassword = encryptService.encrypt(password);

        Optional<MemberLogin> findMember = memberRepository.findMember(requestEmail, requestPassword);

        if (findMember.isPresent()) {
            MemberLogin memberLogin = findMember.get();
            httpSession.setAttribute(SessionKey.LOGIN_MEMBER_ID, memberLogin.getId());

            return new MemberNickname(memberLogin.getNickname());
        } else {
            throw new NotAuthenticationException("not authentication");
        }
    }

    @Override
    public void logout() {
        Optional<Object> member = Optional.ofNullable(httpSession.getAttribute(SessionKey.LOGIN_MEMBER_ID));

        if (member.isPresent()) {
            httpSession.invalidate();
        }
    }
}
