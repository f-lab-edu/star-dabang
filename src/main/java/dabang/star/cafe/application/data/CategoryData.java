package dabang.star.cafe.application.data;

import dabang.star.cafe.domain.category.Category;
import dabang.star.cafe.domain.category.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryData {

    private Integer id;

    private String name;

    private CategoryType categoryType;

    public static CategoryData from(Category category) {

        return new CategoryData(category.getId(),
                category.getName(),
                category.getCategoryType());
    }

}
