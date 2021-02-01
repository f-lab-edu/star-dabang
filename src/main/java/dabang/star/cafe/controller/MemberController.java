package dabang.star.cafe.controller;

import dabang.star.cafe.domain.Member;
import dabang.star.cafe.dto.MemberRequestDto;
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
     * @param memberRequestDto 멤버 회원가입 요청 Form
     * @return 멤버 회원가입 완료시 HttpStatus.Ok, "success" 반환 / 유효성 검증 에러시 HttpStatus.BAD_REQUEST, error message 반환
     */
    @PostMapping
    public ResponseEntity signUpMember(@Valid @RequestBody MemberRequestDto memberRequestDto) {

        if (validEmail(memberRequestDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("duplicated email");
        }

        memberService.join(new Member(memberRequestDto));

        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    private boolean validEmail(String email) {
        return memberService.duplicatedEmail(email);
    }

}
