package dabang.star.cafe.domain.option;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Option {

    private Long id;

    private String name;

    private Integer price;

    private Integer maxQuantity;

}
