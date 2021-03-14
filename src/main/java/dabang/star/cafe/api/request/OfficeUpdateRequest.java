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
    private Long id;

    @NotBlank(message = "blank office name")
    private String name;

    @NotBlank(message = "blank office address")
    @Pattern(regexp = "/(([가-힣A-Za-z·\\d~\\-\\.]{2,}(로|길).[\\d]+)|([가-힣A-Za-z·\\d~\\-\\.]+(읍|동)\\s)[\\d]+)/")
    private String address;

    @NotBlank(message = "blank office latitude")
    private BigDecimal latitude;

    @NotBlank(message = "blank office latitude")
    private BigDecimal longitude;
}
