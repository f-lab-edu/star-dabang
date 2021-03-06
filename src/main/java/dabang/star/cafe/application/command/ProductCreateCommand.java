package dabang.star.cafe.application.command;

import dabang.star.cafe.domain.product.Product;
import dabang.star.cafe.domain.product.ProductOption;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ProductCreateCommand {

    @NotNull(message = "blank name")
    @Size(max = 20, message = "not valid name")
    private String name;

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
    private final List<ProductOptionCommand> options = new ArrayList<>();

    public Product toProduct(int categoryId) {

        List<ProductOption> options = this.options.stream()
                .map(ProductOptionCommand::toProductOption)
                .collect(Collectors.toList());

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
