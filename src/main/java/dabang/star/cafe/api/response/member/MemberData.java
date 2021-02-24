package dabang.star.cafe.api.response.member;

import dabang.star.cafe.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberData {

    private Long id;

    private String email;

    private String nickname;

    public MemberData(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
    }
}
