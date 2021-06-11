package dabang.star.cafe.domain.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Member {

    private Long id;

    private String email;

    private String password;

    private String nickname;

    private String telephone;

    private String birth;

    private String token;

}