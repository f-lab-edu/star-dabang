package dabang.star.cafe.infrastructure.service;

import dabang.star.cafe.api.exception.NotAuthenticationException;
import dabang.star.cafe.api.request.LoginRequest;
import dabang.star.cafe.domain.member.EncryptService;
import dabang.star.cafe.domain.member.LoginService;
import dabang.star.cafe.domain.member.Member;
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
    public Long login(LoginRequest loginRequest) {

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Optional<Member> findMember = memberReadService.findByEmail(email);

        if (findMember.isPresent()) {
            Member member = findMember.get();
            String requestPassword = encryptService.encrypt(password);

            if (member.getPassword().equals(requestPassword)) {
                httpSession.setAttribute("memberId", member.getId());
            } else {
                throw new NotAuthenticationException("not authentication");
            }

            return member.getId();
        } else {
            throw new NotAuthenticationException("not authentication");
        }
    }
}
