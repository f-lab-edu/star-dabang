package dabang.star.cafe.application.data;

import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductData implements Cloneable {

    private Long id;

    private String name;

    private Integer categoryId;

    private Integer price;

    private String description;

    private String image;

    private Boolean isActive;

    private List<ProductOptionData> options;

    public void calcPrice(Map<Long, Integer> myMenuOptions) {
        if (this.options != null && myMenuOptions != null) {
            this.price += this.options.stream()
                    .mapToInt(o -> o.calcOptionPrice(myMenuOptions.get(o.getOptionId())))
                    .sum();
        }
    }

    public ProductData copy() {
        List<ProductOptionData> copyOptions = this.options.stream()
                .map(ProductOptionData::copy)
                .collect(Collectors.toList());

        return ProductData.builder()
                .id(this.id)
                .name(this.name)
                .categoryId(this.categoryId)
                .price(this.price)
                .description(this.description)
                .image(this.image)
                .isActive(this.isActive)
                .options(copyOptions)
                .build();
    }

}
