package dabang.star.cafe.controller;

import dabang.star.cafe.domain.Member;
import dabang.star.cafe.dto.MemberForm;
import dabang.star.cafe.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public void join(@RequestBody MemberForm memberForm) {

        memberService.join(new Member(memberForm));
    }
}
