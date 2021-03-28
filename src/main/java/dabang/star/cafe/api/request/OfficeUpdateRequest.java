package dabang.star.cafe.api.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class OfficeUpdateRequest {

    @NotBlank(message="blank office id")
    private Integer id;

    @NotBlank(message = "blank office name")
    private String name;

    @NotBlank(message = "blank office address")
    private String address;

    @NotBlank(message = "blank office latitude")
    private BigDecimal latitude;

    @NotBlank(message = "blank office latitude")
    private BigDecimal longitude;
}
