package dabang.star.cafe.application.data;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductData {

    private Long id;

    private String name;

    private Integer categoryId;

    private Integer price;

    private String description;

    private String image;

    private Boolean isActive;

    private List<ProductOptionData> options;

}
