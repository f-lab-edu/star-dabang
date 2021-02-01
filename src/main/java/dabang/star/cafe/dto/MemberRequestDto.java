package dabang.star.cafe.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class MemberRequestDto {

    @NotBlank(message = "blank email")
    @Email(message = "not valid email format")
    private final String email;

    @NotBlank(message = "not valid password")
    private final String passwd;

    @NotBlank(message = "not valid nickname")
    private final String nickname;

    @NotBlank(message = "not valid telephone")
    @Pattern(regexp = "[0-9]{10,11}", message = "not valid telephone")
    private final String telephone;

    @NotBlank(message = "not valid birth day")
    private final String birth;
}
