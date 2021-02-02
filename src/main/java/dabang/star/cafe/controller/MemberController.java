package dabang.star.cafe.controller;

import dabang.star.cafe.domain.Member;
import dabang.star.cafe.dto.request.SignUpRequestDto;
import dabang.star.cafe.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    /**
     * 멤버 회원가입
     *
     * @param signUpRequestDto 멤버 회원가입 요청 Form
     * @return 멤버 회원가입 완료시 HttpStatus.Ok 반환 / 유효성 검증 에러시 HttpStatus.BAD_REQUEST, error message 반환
     */
    @PostMapping
    public ResponseEntity signUpMember(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {

        validEmail(signUpRequestDto.getEmail());

        memberService.join(new Member(signUpRequestDto));

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private boolean validEmail(String email) {
        return memberService.duplicatedEmail(email);
    }

}
