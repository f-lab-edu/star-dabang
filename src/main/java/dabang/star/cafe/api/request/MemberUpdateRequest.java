package dabang.star.cafe.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateRequest {

    @NotBlank(message = "blank email")
    @Pattern(regexp = "^[0-9a-zA-z]{2,}$", message = "not valid password")
    private String password;

    @NotBlank(message = "not valid nickname")
    @Pattern(regexp = "^[가-힣]{2,12}$", message = "not valid nickname")
    private String nickname;

    @NotBlank(message = "not valid telephone")
    @Pattern(regexp = "[0-9]{10,11}", message = "not valid telephone")
    private String telephone;
}
