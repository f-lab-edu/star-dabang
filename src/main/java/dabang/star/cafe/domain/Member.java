package dabang.star.cafe.domain;

import dabang.star.cafe.dto.MemberRequestDto;
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

    private String passwd;

    private String nickname;

    private String telephone;

    private String birth;

    public Member(MemberRequestDto memberRequestDto) {
        this.email = memberRequestDto.getEmail();
        this.passwd = memberRequestDto.getPasswd();
        this.nickname = memberRequestDto.getNickname();
        this.telephone = memberRequestDto.getTelephone();
        this.birth = memberRequestDto.getBirth();
    }

    public void encryptPassword(String passwd) {
        this.passwd = passwd;
    }
}