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

}
