package dabang.star.cafe.api;

import dabang.star.cafe.api.aop.MemberLoginCheck;
import dabang.star.cafe.api.request.LoginRequest;
import dabang.star.cafe.api.request.MemberUpdateRequest;
import dabang.star.cafe.api.request.SignUpRequest;
import dabang.star.cafe.api.response.member.MemberData;
import dabang.star.cafe.domain.login.LoginService;
import dabang.star.cafe.domain.member.Member;
import dabang.star.cafe.domain.member.MemberService;
import dabang.star.cafe.utils.ResponseStatus;
import dabang.star.cafe.utils.SessionKey;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
     * @return 멤버 회원가입 완료시 HttpStatus.Ok, ID 반환 / 유효성 검증 에러시 HttpStatus.BAD_REQUEST, error 반환
     */
    @PostMapping
    public ResponseEntity<MemberData> signUpMember(@Valid @RequestBody SignUpRequest signUpRequest) {

        MemberData memberData = memberService.join(new Member(signUpRequest));

        return ResponseEntity.status(HttpStatus.CREATED).body(memberData);
    }

    /**
     * 로그인
     *
     * @param loginRequest (email, password)
     * @return 로그인 완료시 HttpsStatus.OK, ID 반환 / 로그인 실패시 HttpsStatus.UNAUTHORIZED, error 반환
     */
    @PostMapping("/login")
    public ResponseEntity<MemberData> loginMember(@Valid @RequestBody LoginRequest loginRequest) {

        MemberData memberData = loginService.login(loginRequest.getEmail(), loginRequest.getPassword());

        return ResponseEntity.status(HttpStatus.OK).body(memberData);
    }

    /**
     * 로그아웃
     *
     * @return 로그아웃 완료시 HttpsStatus.OK, 반환
     */
    @PostMapping("/logout")
    public ResponseEntity logoutMember() {

        loginService.logout();

        return ResponseStatus.OK;
    }


    /**
     * 멤버 업데이트
     * @param memberUpdateRequest (password, nickname, telephone)
     * @param httpSession (httpSession)
     * @return 업데이트 완료시 HttpStatus.OK 반환
     */
    @MemberLoginCheck
    @PatchMapping
    public ResponseEntity<MemberData> updateMember(@Valid @RequestBody MemberUpdateRequest memberUpdateRequest,
                                                   HttpSession httpSession) {

        Long id = (Long) httpSession.getAttribute(SessionKey.LOGIN_MEMBER_ID);
        memberService.update(new Member(id, memberUpdateRequest));

        MemberData memberData = memberService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(memberData);
    }
}
