package dabang.star.cafe.application.data;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
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

    public ProductOptionData copy() {
        return ProductOptionData.builder()
                .productId(this.productId)
                .optionId(this.optionId)
                .optionName(this.optionName)
                .optionPrice(this.optionPrice)
                .originQuantity(this.originQuantity)
                .presentQuantity(this.presentQuantity)
                .maxQuantity(this.maxQuantity)
                .build();
    }

}
