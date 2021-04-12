package dabang.star.cafe.domain.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ProductOption {

    private Long productId;

    private Long optionId;

    private Integer quantity;

    public void setProductId(Long productId) {
        this.productId = productId;
    }

}
