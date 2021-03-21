package dabang.star.cafe.api.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class OptionCreateRequest {

    @NotNull(message = "blank name")
    private String name;

    @NotNull(message = "blank price")
    @PositiveOrZero(message = "not valid price")
    private Integer price;

    @NotNull(message = "blank max quantity")
    @Positive(message = "not valid max quantity")
    private Integer maxQuantity;

}
