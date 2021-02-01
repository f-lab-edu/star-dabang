package dabang.star.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
public class MemberRequestDto {

    @NotBlank(message = "blank email")
    @Email(message = "not valid email format")
    private String email;

    @NotBlank(message = "not valid password")
    private String passwd;

    @NotBlank(message = "not valid nickname")
    private String nickname;

    @NotBlank(message = "not valid telephone")
    @Pattern(regexp = "[0-9]{10,11}", message = "not valid telephone")
    private String telephone;

    @NotBlank(message = "not valid birth day")
    private String birth;
}
