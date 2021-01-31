package dabang.star.cafe.controller;

import dabang.star.cafe.domain.Member;
import dabang.star.cafe.dto.MemberForm;
import dabang.star.cafe.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity join(@Valid @RequestBody MemberForm memberForm) {

        memberService.join(new Member(memberForm));

        return ResponseEntity.status(HttpStatus.OK).body("success");
    }
}
