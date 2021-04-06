package dabang.star.cafe.application.command;

import dabang.star.cafe.domain.option.Option;
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
public class OptionUpdateCommand {

    @NotNull(message = "blank option id")
    private Integer id;

    @NotNull(message = "blank option name")
    private String name;

    @NotNull(message = "blank option price")
    @PositiveOrZero(message = "not valid option price")
    private Integer price;

    @NotNull(message = "blank option max quantity")
    @Positive(message = "not valid option max quantity")
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
