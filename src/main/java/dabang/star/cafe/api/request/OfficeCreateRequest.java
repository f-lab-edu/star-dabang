package dabang.star.cafe.api.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class OfficeCreateRequest {

    @NotBlank(message = "blank office name")
    private String name;

    @NotBlank(message = "blank office address")
    private String address;

    @NotNull(message = "blank office latitude")
    private BigDecimal latitude;

    @NotNull(message = "blank office latitude")
    private BigDecimal longitude;
}
