package dabang.star.cafe.domain;

import dabang.star.cafe.dto.MemberForm;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Member {

    private Long id;

    private String email;

    private String passwd;

    private String nickname;

    private String telephone;

    private String birth;

    @Builder
    public Member(Long id, String email, String passwd, String nickname, String telephone, String birth) {
        this.id = id;
        this.email = email;
        this.passwd = passwd;
        this.nickname = nickname;
        this.telephone = telephone;
        this.birth = birth;
    }

    public Member(MemberForm memberForm) {
        this.email = memberForm.getEmail();
        this.passwd = memberForm.getPasswd();
        this.nickname = memberForm.getNickname();
        this.telephone = memberForm.getTelephone();
        this.birth = memberForm.getBirth();
    }
}