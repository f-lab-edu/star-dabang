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
public class OptionUpdateCommand {

    @NotNull(message = "blank option id")
    private Long id;

    @NotBlank(message = "blank option name")
    @Size(max = 10, message = "not valid name")
    private String name;

    @NotNull(message = "blank option price")
    @PositiveOrZero(message = "not valid option price")
    @Max(value = 65535)
    private Integer price;

    @NotNull(message = "blank option max quantity")
    @Positive(message = "not valid option max quantity")
    @Max(value = 255)
    private Integer maxQuantity;

    public Option toOption() {

        return Option.builder()
                .id(id)
                .name(name)
                .price(price)
                .maxQuantity(maxQuantity)
                .build();
    }
    
}
