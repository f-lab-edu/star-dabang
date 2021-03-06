package dabang.star.cafe.api;

import dabang.star.cafe.api.aop.CurrentMemberCheck;
import dabang.star.cafe.api.aop.LoginCheck;
import dabang.star.cafe.api.aop.SessionId;
import dabang.star.cafe.api.request.CurrentMemberRequest;
import dabang.star.cafe.application.MemberService;
import dabang.star.cafe.application.command.MemberUpdateCommand;
import dabang.star.cafe.application.data.MemberData;
import dabang.star.cafe.domain.authentication.MemberLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/members/my-info")
@RequiredArgsConstructor
public class CurrentMemberApi {

    private final MemberService memberService;
    private final MemberLoginService memberLoginService;

    /**
     * 마이페이지(수정, 탈퇴) 접속
     *
     * @param currentMemberRequest (password)
     * @param loginMemberId        로그인 유저 ID: 스프링 AOP 통해 주입
     * @return 조회 성공시 HttpStatus.OK(MemberData) 반환
     */
    @LoginCheck
    @PostMapping
    public MemberData myPage(@Valid @RequestBody CurrentMemberRequest currentMemberRequest,
                             @SessionId Long loginMemberId) {

        return memberLoginService.accessMyPage(loginMemberId, currentMemberRequest.getPassword());
    }

    /**
     * 멤버 업데이트
     *
     * @param memberUpdateCommand (password, nickname, telephone)
     * @param currentMemberId     현재 유저 ID: 스프링 AOP 통해 주입
     *                            완료시 HttpStatus.NO_CONTENT 반환
     */
    @CurrentMemberCheck
    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMember(@Valid @RequestBody MemberUpdateCommand memberUpdateCommand,
                             @SessionId Long currentMemberId) {

        memberService.update(memberUpdateCommand, currentMemberId);
    }

    /**
     * 멤버 탈퇴
     *
     * @param currentMemberId 현재 유저 ID: 스프링 AOP 통해 주입
     *                        삭제 완료시 HttpStatus.NO_CONTENT 반환
     */
    @CurrentMemberCheck
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMember(@SessionId Long currentMemberId) {

        memberService.secession(currentMemberId);

        memberLoginService.logout();
    }

}