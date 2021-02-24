package dabang.star.cafe.infrastructure.service;

import dabang.star.cafe.api.exception.MemberNotFoundException;
import dabang.star.cafe.api.response.member.MemberData;
import dabang.star.cafe.domain.login.LoginService;
import dabang.star.cafe.domain.member.EncryptService;
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
    public MemberData login(String email, String password) {

        String requestEmail = email;
        String requestPassword = encryptService.encrypt(password);

        Optional<MemberData> findMember = memberRepository.findMember(requestEmail, requestPassword);

        if (findMember.isPresent()) {
            MemberData memberData = findMember.get();
            httpSession.setAttribute(SessionKey.LOGIN_MEMBER_ID, memberData.getId());

            return memberData;
        } else {
            throw new MemberNotFoundException("member not found");
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
