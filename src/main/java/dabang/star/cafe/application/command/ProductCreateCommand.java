package dabang.star.cafe.application.command;

import dabang.star.cafe.domain.product.Product;
import dabang.star.cafe.domain.product.ProductOption;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ProductCreateCommand {

    @NotNull(message = "blank name")
    @Size(max = 20, message = "not valid name")
    private String name;

    @NotNull(message = "blank category id")
    @Positive(message = "not valid category id")
    @Max(value = 65535)
    private Integer categoryId;

    @NotNull(message = "blank price")
    @PositiveOrZero(message = "not valid price")
    @Max(value = 65535)
    private Integer price;

    @NotNull(message = "blank description")
    @Size(max = 255, message = "not valid description")
    private String description;

    @NotNull(message = "blank image url")
    private String image;

    // FOOD 같은 경우는 옵션이 존재하지 않을 수 있다.
    private List<ProductOptionCommand> options;

    public Product toProduct() {

        // product에 대한 option을 설정하였을 경우에만 값을 설정한다.
        List<ProductOption> options = null;
        if (this.options != null) {
            options = this.options.stream()
                    .map(ProductOptionCommand::toProductOption)
                    .collect(Collectors.toList());
        }

        return Product.builder()
                .name(name)
                .categoryId(categoryId)
                .price(price)
                .description(description)
                .image(image)
                .options(options)
                .isActive(true)
                .createAt(LocalDate.now())
                .build();
    }

}
