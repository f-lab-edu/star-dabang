package dabang.star.cafe.domain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderProduct {

    private Long id;

    private Long orderId;

    private Long productId;

    private Integer quantity;

    private Map<Long, Integer> optionInfo;

    private Integer price;

}
