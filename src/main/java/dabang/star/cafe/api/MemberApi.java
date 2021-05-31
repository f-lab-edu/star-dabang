package dabang.star.cafe.api;

import dabang.star.cafe.api.aop.LoginCheck;
import dabang.star.cafe.api.aop.SessionId;
import dabang.star.cafe.api.request.MemberLoginRequest;
import dabang.star.cafe.application.MemberService;
import dabang.star.cafe.application.command.SignUpCommand;
import dabang.star.cafe.application.data.MemberData;
import dabang.star.cafe.domain.authentication.MemberLoginService;
import dabang.star.cafe.domain.push.PushService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberApi {

    private final MemberService memberService;
    private final MemberLoginService memberLoginService;
    private final PushService pushService;

    /**
     * 멤버 회원가입
     *
     * @param signUpCommand (email, password, nickname, telephone, birth)
     * @return 멤버 회원가입 완료시 HttpStatus.Ok (MemberData) 반환
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberData signUpMember(@Valid @RequestBody SignUpCommand signUpCommand) {
        return memberService.join(signUpCommand);
    }

    /**
     * 멤버 로그인
     *
     * @param loginRequest (email, password)
     * @return 로그인 완료시 HttpsStatus.OK (MemberData) 반환
     */
    @PostMapping("/login")
    public MemberData loginMember(@Valid @RequestBody MemberLoginRequest loginRequest) {
        return memberLoginService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }

    /**
     * 멤버 로그아웃
     * <p>
     * 로그아웃 완료시 HttpsStatus.OK 반환
     */
    @PostMapping("/logout")
    public void logoutMember() {
        memberLoginService.logout();
    }

    @LoginCheck
    @PostMapping("/token")
    public void registerToken(String token, @SessionId Long id) {
        pushService.saveToken(id, token);
    }

}