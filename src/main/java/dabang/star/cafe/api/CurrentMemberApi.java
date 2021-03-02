package dabang.star.cafe.api;

import dabang.star.cafe.api.aop.CurrentMemberCheck;
import dabang.star.cafe.api.aop.MemberLoginCheck;
import dabang.star.cafe.api.request.CurrentMemberRequest;
import dabang.star.cafe.api.request.MemberUpdateRequest;
import dabang.star.cafe.api.response.member.MemberData;
import dabang.star.cafe.domain.login.LoginService;
import dabang.star.cafe.domain.member.Member;
import dabang.star.cafe.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/members/me")
@RequiredArgsConstructor
public class CurrentMemberApi {

    private final MemberService memberService;
    private final LoginService loginService;

    /**
     * 마이페이지(수정, 탈퇴) 접속
     *
     * @param currentMemberRequest (password)
     * @param loginMemberId        로그인 유저 ID: 스프링 AOP 통해 주입
     * @return 조회 성공시 HttpStatus.OK(MemberData) 반환
     */
    @CurrentMemberCheck
    @PostMapping
    public MemberData myPage(@Valid @RequestBody CurrentMemberRequest currentMemberRequest,
                             Long loginMemberId) {

        return loginService.accessToMyPage(loginMemberId, currentMemberRequest.getPassword());
    }

    /**
     * 멤버 업데이트
     *
     * @param memberUpdateRequest (password, nickname, telephone)
     * @param currentMemberId     현재 유저 ID: 스프링 AOP 통해 주입
     * @return 업데이트 완료시 HttpStatus.NO_CONTENT 반환
     */
    @CurrentMemberCheck
    @PatchMapping
    public ResponseEntity<Void> updateMember(@Valid @RequestBody MemberUpdateRequest memberUpdateRequest,
                                             Long currentMemberId) {

        memberService.update(new Member(currentMemberId, memberUpdateRequest));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 멤버 삭제
     *
     * @param loginMemberId 로그인 유저 ID: 스프링 AOP 통해 주입
     *                      삭제 완료시 HttpStatus.NO_CONTENT 반환
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @MemberLoginCheck
    @DeleteMapping
    public void deleteMember(Long loginMemberId) {

        memberService.secession(loginMemberId);

        loginService.logout();
    }

}