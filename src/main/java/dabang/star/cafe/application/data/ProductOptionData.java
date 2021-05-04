package dabang.star.cafe.application.data;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductOptionData implements Cloneable {

    private Long productId;

    private Long optionId;

    private String optionName;

    private Integer optionPrice;

    private Integer originQuantity;

    private Integer presentQuantity;

    private Integer maxQuantity;

    public int calcOptionPrice(int quantity) {
        this.presentQuantity = quantity;
        int diffQuantity = quantity - this.originQuantity;
        return diffQuantity > 0 ? diffQuantity * this.optionPrice : 0;
    }

    @Override
    public ProductOptionData clone() {
        try {
            return (ProductOptionData) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

}
