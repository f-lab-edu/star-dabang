package dabang.star.cafe.api;

import dabang.star.cafe.api.request.LoginRequest;
import dabang.star.cafe.api.request.SignUpRequest;
import dabang.star.cafe.api.response.member.MemberData;
import dabang.star.cafe.domain.login.LoginService;
import dabang.star.cafe.domain.member.Member;
import dabang.star.cafe.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberApi {

    private final MemberService memberService;
    private final LoginService loginService;

    /**
     * 멤버 회원가입
     *
     * @param signUpRequest (email, password, nickname, telephone, birth)
     * @return 멤버 회원가입 완료시 HttpStatus.Ok (MemberData) 반환
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberData signUpMember(@Valid @RequestBody SignUpRequest signUpRequest) {
        return memberService.join(new Member(signUpRequest));
    }

    /**
     * 로그인
     *
     * @param loginRequest (email, password)
     * @return 로그인 완료시 HttpsStatus.OK (MemberData) 반환
     */
    @PostMapping("/login")
    public MemberData loginMember(@Valid @RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }

    /**
     * 로그아웃
     *
     * 로그아웃 완료시 HttpsStatus.OK 반환
     */
    @PostMapping("/logout")
    public void logoutMember() {
        loginService.logout();
    }

}