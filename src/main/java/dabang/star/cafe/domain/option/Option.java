package dabang.star.cafe.domain.option;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class Option {

    private Integer id;

    private String name;

    private Integer price;

    private Integer maxQuantity;

}
