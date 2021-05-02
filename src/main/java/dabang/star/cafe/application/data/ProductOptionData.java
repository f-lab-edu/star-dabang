package dabang.star.cafe.application.data;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductOptionData {

    private Long productId;

    private Long optionId;

    private String optionName;

    private Integer optionPrice;

    private Integer originQuantity;

    private Integer presentQuantity;

    private Integer maxQuantity;

    public int calcOptionPrice(int quantity) {
        int optionPrice = 0;
        int diffQuantity = quantity - this.originQuantity;
        if (diffQuantity > 0) {
            optionPrice = diffQuantity * this.optionPrice;
        }
        this.presentQuantity = quantity;

        return optionPrice;
    }

}
