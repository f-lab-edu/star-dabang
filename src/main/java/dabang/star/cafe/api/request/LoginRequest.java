package dabang.star.cafe.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "blank email")
    @Email(message = "not valid email format")
    private String email;

    @NotBlank(message = "not valid password")
    @Pattern(regexp = "^[0-9a-zA-z]{2,}$", message = "not valid password")
    private String password;
}
