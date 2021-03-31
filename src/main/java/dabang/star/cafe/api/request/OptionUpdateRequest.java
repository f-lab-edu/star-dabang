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
public class OptionUpdateRequest {

    @NotNull(message = "blank option id")
    private int id;

    @NotNull(message = "blank option name")
    private String name;

    @NotNull(message = "blank option price")
    @PositiveOrZero(message = "not valid option price")
    private int price;

    @NotNull(message = "blank option max quantity")
    @Positive(message = "not valid option max quantity")
    private int maxQuantity;

}
