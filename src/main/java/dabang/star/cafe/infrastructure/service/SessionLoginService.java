package dabang.star.cafe.infrastructure.service;

import dabang.star.cafe.api.exception.NotAuthenticationException;
import dabang.star.cafe.application.data.MemberId;
import dabang.star.cafe.domain.member.EncryptService;
import dabang.star.cafe.domain.login.LoginService;
import dabang.star.cafe.infrastructure.mapper.read.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SessionLoginService implements LoginService {

    private final HttpSession httpSession;
    private final EncryptService encryptService;
    private final MemberReadService memberReadService;

    @Override
    public MemberId login(String email, String password) {

        String requestEmail = email;
        String requestPassword = encryptService.encrypt(password);

        Optional<Long> findMemberId = memberReadService.findByEmailAndPassword(requestEmail, requestPassword);

        if (findMemberId.isPresent()) {
            Long memberId = findMemberId.get();
            httpSession.setAttribute("MEMBER", memberId);

            return new MemberId(memberId);
        } else {
            throw new NotAuthenticationException("not authentication");
        }
    }

    @Override
    public void logout() {
        Optional<Object> member = Optional.ofNullable(httpSession.getAttribute("MEMBER"));

        if (member.isPresent()) {
            httpSession.invalidate();
        }
    }
}
