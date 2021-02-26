package dabang.star.cafe.api;

import dabang.star.cafe.api.aop.MemberLoginCheck;
import dabang.star.cafe.domain.login.LoginService;
import dabang.star.cafe.domain.member.MemberService;
import dabang.star.cafe.utils.SessionKey;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController("/member")
public class MemberInfoApi {

    private final MemberService memberService;
    private final LoginService loginService;

    /**
     * 멤버 회원탈퇴
     */
    @MemberLoginCheck
    @DeleteMapping
    public void deleteMember(HttpSession httpSession) {

        Long id = (Long) httpSession.getAttribute(SessionKey.LOGIN_MEMBER_ID);
        memberService.secession(id);

        loginService.logout();
    }

}
