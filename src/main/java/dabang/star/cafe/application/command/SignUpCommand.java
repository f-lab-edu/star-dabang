package dabang.star.cafe.application.command;

import dabang.star.cafe.domain.member.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SignUpCommand {

    @NotBlank(message = "not valid email")
    @Email(message = "not valid email")
    private String email;

    @NotBlank(message = "not valid password")
    @Pattern(regexp = "^[0-9a-zA-z]{2,}$", message = "not valid password")
    private String password;

    @NotBlank(message = "not valid nickname")
    @Pattern(regexp = "^[가-힣]{2,12}$", message = "not valid nickname")
    private String nickname;

    @NotBlank(message = "not valid telephone")
    @Pattern(regexp = "[0-9]{10,11}", message = "not valid telephone")
    private String telephone;

    @NotBlank(message = "not valid birthday")
    @Pattern(regexp = "[0-9]{8}", message = "not valid birthday")
    private String birth;

    public Member toMember(String encryptPassword) {

        return Member.builder()
                .email(email)
                .password(encryptPassword)
                .nickname(nickname)
                .telephone(telephone)
                .birth(birth)
                .build();
    }

}
