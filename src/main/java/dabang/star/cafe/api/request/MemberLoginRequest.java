package dabang.star.cafe.api.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MemberLoginRequest {

    @NotBlank(message = "blank email")
    @Email(message = "not valid email format")
    private String email;

    @NotBlank(message = "blank password")
    private String password;
}
