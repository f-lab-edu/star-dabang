package dabang.star.cafe.application.data;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductData implements Cloneable {

    private Long id;

    private String name;

    private Integer categoryId;

    private Integer price;

    private String description;

    private String image;

    private Boolean isActive;

    @Setter
    private List<ProductOptionData> options;

    public void calcPrice(Map<Long, Integer> myMenuOptions) {
        if (this.options != null && myMenuOptions != null) {
            this.price += this.options.stream()
                    .mapToInt(o -> o.calcOptionPrice(myMenuOptions.get(o.getOptionId())))
                    .sum();
        }
    }

    @Override
    public ProductData clone() {
        ProductData productData;
        try {
            productData = (ProductData) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        if (this.options != null) {
            List<ProductOptionData> copyOptions = this.options.stream()
                    .map(ProductOptionData::clone)
                    .collect(Collectors.toList());
            productData.setOptions(copyOptions);
        }

        return productData;
    }
}
