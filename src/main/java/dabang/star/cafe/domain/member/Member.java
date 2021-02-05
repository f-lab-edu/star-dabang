package dabang.star.cafe.domain.member;

import dabang.star.cafe.api.request.SignUpRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
@AllArgsConstructor
public class Member {

    private Long id;

    private String email;

    private String password;

    private String nickname;

    private String telephone;

    private String birth;

    public void setPassword(String password) {
        this.password = password;
    }

    public Member(SignUpRequest signUpRequest) {
        this.email = signUpRequest.getEmail();
        this.password = signUpRequest.getPassword();
        this.nickname = signUpRequest.getNickname();
        this.telephone = signUpRequest.getTelephone();
        this.birth = signUpRequest.getBirth();
    }

}