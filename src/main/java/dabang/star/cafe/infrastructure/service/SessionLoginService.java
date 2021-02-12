package dabang.star.cafe.infrastructure.service;

import dabang.star.cafe.api.exception.NotAuthenticationException;
import dabang.star.cafe.application.data.MemberLogin;
import dabang.star.cafe.application.data.MemberNickname;
import dabang.star.cafe.domain.member.EncryptService;
import dabang.star.cafe.domain.login.LoginService;
import dabang.star.cafe.infrastructure.mapper.read.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static dabang.star.cafe.utils.Common.SESSION_MEMBER_KEY;

@RequiredArgsConstructor
@Service
public class SessionLoginService implements LoginService {

    private final HttpSession httpSession;
    private final EncryptService encryptService;
    private final MemberReadService memberReadService;

    @Override
    public MemberNickname login(String email, String password) {

        String requestEmail = email;
        String requestPassword = encryptService.encrypt(password);

        Optional<MemberLogin> findMember = memberReadService.getMemberByLogin(requestEmail, requestPassword);

        if (findMember.isPresent()) {
            MemberLogin memberLogin = findMember.get();
            httpSession.setAttribute(SESSION_MEMBER_KEY, memberLogin.getId());

            return new MemberNickname(memberLogin.getNickname());
        } else {
            throw new NotAuthenticationException("not authentication");
        }
    }

    @Override
    public void logout() {
        Optional<Object> member = Optional.ofNullable(httpSession.getAttribute(SESSION_MEMBER_KEY));

        if (member.isPresent()) {
            httpSession.invalidate();
        }
    }
}
