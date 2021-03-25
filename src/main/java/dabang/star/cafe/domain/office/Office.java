package dabang.star.cafe.domain.office;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class Office {

    private Long id;

    private String name;

    private String address;

    private BigDecimal latitude;

    private BigDecimal longitude;
}
