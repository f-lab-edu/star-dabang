package dabang.star.cafe.application.data;

import dabang.star.cafe.domain.category.CategoryType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TypeCategoryData {

    private CategoryType type;

    private List<CategoryData> category;

}
