package dabang.star.cafe.domain.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class Product {

    private Long id;

    private String name;

    private Integer categoryId;

    private Integer price;

    private String description;

    private String image;

    private Boolean isActive;

    private LocalDate createAt;

    private List<ProductOption> options;

}
