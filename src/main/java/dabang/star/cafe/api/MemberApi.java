package dabang.star.cafe.api;

import dabang.star.cafe.domain.member.Member;
import dabang.star.cafe.api.request.SignUpRequest;
import dabang.star.cafe.application.MemberApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberApi {

    private final MemberApplicationService memberApplicationService;

    /**
     * 멤버 회원가입
     *
     * @param SignUpRequest 멤버 회원가입 요청 Form
     * @return 멤버 회원가입 완료시 HttpStatus.Ok, ID 반환 / 유효성 검증 에러시 HttpStatus.BAD_REQUEST, error message 반환
     */
    @PostMapping()
    public ResponseEntity signUpMember(@Valid @RequestBody SignUpRequest signUpRequest) {

        Long id = memberApplicationService.join(new Member(signUpRequest));

        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    /**
     * 로그인
     */
}
