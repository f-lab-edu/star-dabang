package dabang.star.cafe.api.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ManagerCreateRequest {

    @NotBlank(message = "invalid name")
    private String name;

    @NotBlank(message = "invalid password")
    private String password;

    @NotBlank(message = "invalid office")
    private String officeName;
}
