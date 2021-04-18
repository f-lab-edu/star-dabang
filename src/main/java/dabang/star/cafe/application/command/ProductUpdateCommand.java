package dabang.star.cafe.application.command;

import dabang.star.cafe.domain.product.Product;
import dabang.star.cafe.domain.product.ProductOption;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ProductUpdateCommand {

    @Size(max = 20, message = "not valid name")
    private String name;

    @PositiveOrZero(message = "not valid price")
    @Max(value = 65535)
    private Integer price;

    @Size(max = 255, message = "not valid description")
    private String description;

    private String image;

    private Boolean isActive;

    // 기존 옵션에서 Quantity가 변경될 옵션
    private final List<ProductOptionCommand> options = new ArrayList<>();
    // 기존 옵션에서 추가될 옵션
    private final List<ProductOptionCommand> addOptions = new ArrayList<>();
    // 기존 옵션에서 삭제할 옵션
    private final List<Long> deleteOptions = new ArrayList<>();

    public Product toProduct(int categoryId, long productId) {

        List<ProductOption> options = this.options.stream()
                .map(ProductOptionCommand::toProductOption)
                .collect(Collectors.toList());

        return Product.builder()
                .id(productId)
                .name(name)
                .categoryId(categoryId)
                .price(price)
                .description(description)
                .image(image)
                .options(options)
                .isActive(isActive)
                .build();
    }

    public List<ProductOption> getAddOptions() {
        return this.addOptions.stream()
                .map(ProductOptionCommand::toProductOption)
                .collect(Collectors.toList());
    }

}
