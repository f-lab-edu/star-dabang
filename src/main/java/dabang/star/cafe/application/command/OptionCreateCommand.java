package dabang.star.cafe.application.command;

import dabang.star.cafe.domain.option.Option;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class OptionCreateCommand {

    @NotBlank(message = "blank name")
    @Size(max = 10, message = "not valid name")
    private String name;

    @NotNull(message = "blank price")
    @PositiveOrZero(message = "not valid price")
    @Max(value = 65535)
    private Integer price;

    @NotNull(message = "blank max quantity")
    @Positive(message = "not valid max quantity")
    @Max(value = 255)
    private Integer maxQuantity;

    public Option toOption() {

        return Option.builder()
                .name(name)
                .price(price)
                .maxQuantity(maxQuantity)
                .build();
    }

}
