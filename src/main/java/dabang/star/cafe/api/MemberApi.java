package dabang.star.cafe.api;

import dabang.star.cafe.api.request.LoginRequest;
import dabang.star.cafe.application.data.MemberId;
import dabang.star.cafe.application.data.MemberNickname;
import dabang.star.cafe.domain.login.LoginService;
import dabang.star.cafe.domain.member.Member;
import dabang.star.cafe.api.request.SignUpRequest;
import dabang.star.cafe.application.MemberApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static dabang.star.cafe.utils.ResponseStatus.RESPONSE_STATUS_OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberApi {

    private final MemberApplicationService memberApplicationService;
    private final LoginService loginService;

    /**
     * 멤버 회원가입
     *
     * @param  signUpRequest (email, password, nickname, telephone, birth)
     * @return 멤버 회원가입 완료시 HttpStatus.Ok, ID 반환 / 유효성 검증 에러시 HttpStatus.BAD_REQUEST, error 반환
     */
    @PostMapping()
    public ResponseEntity<MemberId> signUpMember(@Valid @RequestBody SignUpRequest signUpRequest) {

        MemberId memberId = memberApplicationService.join(new Member(signUpRequest));

        return ResponseEntity.status(HttpStatus.OK).body(memberId);
    }

    /**
     * 로그인
     * @param loginRequest (email, password)
     * @return 로그인 완료시 HttpsStatus.OK, ID 반환 / 로그인 실패시 HttpsStatus.UNAUTHORIZED, error 반환
     */
    @PostMapping("/login")
    public ResponseEntity<MemberNickname> loginMember(@Valid @RequestBody LoginRequest loginRequest) {

        MemberNickname memberNickname = loginService.login(loginRequest.getEmail(), loginRequest.getPassword());

        return ResponseEntity.status(HttpStatus.OK).body(memberNickname);
    }

    /**
     * 로그아웃
     *
     * @return 로그아웃 완료시 HttpsStatus.OK, 반환
     */
    @PostMapping("/logout")
    public ResponseEntity logoutMember() {

        loginService.logout();

        return RESPONSE_STATUS_OK;
    }
}
