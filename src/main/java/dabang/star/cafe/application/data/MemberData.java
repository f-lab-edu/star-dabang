package dabang.star.cafe.application.data;

import dabang.star.cafe.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberData {

    private Long id;

    private String email;

    private String nickname;

    public static MemberData from(Member member) {

        return new MemberData(member.getId(),
                member.getEmail(),
                member.getNickname());
    }
}
