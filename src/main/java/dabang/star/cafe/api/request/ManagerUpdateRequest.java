package dabang.star.cafe.api.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ManagerUpdateRequest {

    @NotNull(message = "blank id")
    private Long id;

    @NotBlank(message = "blank password")
    private String password;

    @NotBlank(message = "blank office_name")
    private String officeName;
}
