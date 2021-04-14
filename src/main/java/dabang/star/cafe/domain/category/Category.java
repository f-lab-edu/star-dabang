package dabang.star.cafe.domain.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Category {

    private Integer id;

    private String name;

    private CategoryType categoryType;

}
