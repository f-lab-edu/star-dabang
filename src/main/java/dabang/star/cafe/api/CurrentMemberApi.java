package dabang.star.cafe.api;

import dabang.star.cafe.api.aop.CurrentMemberCheck;
import dabang.star.cafe.api.aop.MemberLoginCheck;
import dabang.star.cafe.api.request.CurrentMemberRequest;
import dabang.star.cafe.api.request.MemberUpdateRequest;
import dabang.star.cafe.api.response.member.MemberData;
import dabang.star.cafe.domain.login.LoginService;
import dabang.star.cafe.domain.member.Member;
import dabang.star.cafe.domain.member.MemberService;
import dabang.star.cafe.utils.SessionKey;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/members/my-info")
@RequiredArgsConstructor
public class CurrentMemberApi {

    private final MemberService memberService;
    private final LoginService loginService;

    private final HttpSession httpSession;

    /**
     * 마이페이지(수정, 탈퇴) 접속
     *
     * @param currentMemberRequest (password)
     * @param loginMemberId        로그인 유저 ID: 스프링 AOP 통해 주입
     * @return 조회 성공시 HttpStatus.OK(MemberData) 반환
     */
    @MemberLoginCheck
    @PostMapping
    public MemberData myPage(@Valid @RequestBody CurrentMemberRequest currentMemberRequest,
                             Long loginMemberId) {

        MemberData memberData = memberService.loadByIdAndPassword(loginMemberId, currentMemberRequest.getPassword());
        httpSession.setAttribute(SessionKey.CURRENT_MEMBER_ID, memberData.getId());

        return memberData;
    }

    /**
     * 멤버 업데이트
     *
     * @param memberUpdateRequest (password, nickname, telephone)
     * @param currentMemberId     현재 유저 ID: 스프링 AOP 통해 주입
     *                            완료시 HttpStatus.NO_CONTENT 반환
     */
    @CurrentMemberCheck
    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMember(@Valid @RequestBody MemberUpdateRequest memberUpdateRequest,
                             Long currentMemberId) {

        memberService.update(new Member(currentMemberId, memberUpdateRequest));
    }

    /**
     * 멤버 탈퇴
     *
     * @param currentMemberId 현재 유저 ID: 스프링 AOP 통해 주입
     *                      삭제 완료시 HttpStatus.NO_CONTENT 반환
     */
    @CurrentMemberCheck
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMember(Long currentMemberId) {

        memberService.secession(currentMemberId);

        loginService.logout();
    }

}