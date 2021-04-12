package dabang.star.cafe.application.data;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductOptionData {

    private Long optionId;

    private String optionName;

    private Integer optionPrice;

    private Integer quantity;

    private Integer maxQuantity;

}
