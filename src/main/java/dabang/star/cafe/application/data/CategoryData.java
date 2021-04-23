package dabang.star.cafe.application.data;

import dabang.star.cafe.domain.category.Category;
import dabang.star.cafe.domain.category.CategoryType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
