package dabang.star.cafe.application.data;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductData {

    private Long id;

    private String name;

    private Integer categoryId;

    private Integer price;

    private String description;

    private String image;

    private Boolean isActive;

    private List<ProductOptionData> options;

    public void calcPrice(Map<Long, Integer> myMenuOptions) {
        if (this.options == null || myMenuOptions == null) return;

        int additionOptionPrice = 0;
        for (ProductOptionData option : this.options) {
            int myMenuOptionQuantity = myMenuOptions.get(option.getOptionId());
            additionOptionPrice += option.calcOptionPrice(myMenuOptionQuantity);
        }
        this.price += additionOptionPrice;
    }

}
