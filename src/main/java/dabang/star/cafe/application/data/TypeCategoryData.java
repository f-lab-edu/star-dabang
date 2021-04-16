package dabang.star.cafe.application.data;

import dabang.star.cafe.domain.category.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TypeCategoryData {

    private CategoryType type;

    private List<CategoryData> category;

}
