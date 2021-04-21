package dabang.star.cafe.application.command;

import dabang.star.cafe.domain.product.ProductOption;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ProductOptionCommand {

    @NotNull(message = "blank option id")
    @Positive(message = "not valid option id")
    private Long optionId;

    @NotNull(message = "blank quantity")
    @Positive(message = "not valid quantity")
    private Integer quantity;

    public ProductOption toProductOption() {
        return ProductOption.builder()
                .optionId(optionId)
                .quantity(quantity)
                .build();
    }

}
