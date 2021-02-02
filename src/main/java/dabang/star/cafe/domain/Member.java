package dabang.star.cafe.domain;

import dabang.star.cafe.dto.request.SignUpRequestDto;
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

    public Member(SignUpRequestDto signUpRequestDto) {
        this.email = signUpRequestDto.getEmail();
        this.password = signUpRequestDto.getPassword();
        this.nickname = signUpRequestDto.getNickname();
        this.telephone = signUpRequestDto.getTelephone();
        this.birth = signUpRequestDto.getBirth();
    }

}