package dabang.star.cafe.api.request;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@JsonRootName("user")
@NoArgsConstructor
public class LoginParam {

    @NotBlank(message = "can't be empty")
    @Email(message = "should be an email")
    private String email;

    @NotBlank(message = "can't be empty")
    private String password;
}
