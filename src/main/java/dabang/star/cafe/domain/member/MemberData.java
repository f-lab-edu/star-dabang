package dabang.star.cafe.domain.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberData {

    private Long id;

    private String email;

    private String nickname;

    @Builder
    public static MemberData from(Member member) {

        return new MemberData(member.getId(),
                member.getEmail(),
                member.getNickname());
    }
}
