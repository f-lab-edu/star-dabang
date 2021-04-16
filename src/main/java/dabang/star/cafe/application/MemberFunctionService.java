package dabang.star.cafe.application;

import dabang.star.cafe.application.data.CategoryData;
import dabang.star.cafe.application.data.TypeCategoryData;
import dabang.star.cafe.domain.category.CategoryRepository;
import dabang.star.cafe.domain.category.CategoryType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberFunctionService {

    private final CategoryRepository categoryRepository;

    public List<TypeCategoryData> getCategories() {
        List<TypeCategoryData> typeCategories = new ArrayList<>();
        CategoryType[] types = CategoryType.values();

        for (CategoryType type : types) {
            List<CategoryData> categoryData = categoryRepository.findAllByType(type.getKey());
            typeCategories.add(new TypeCategoryData(type, categoryData));
        }

        return typeCategories;
    }

}
