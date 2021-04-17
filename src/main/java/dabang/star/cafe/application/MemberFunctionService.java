package dabang.star.cafe.application;

import dabang.star.cafe.application.data.CategoryData;
import dabang.star.cafe.application.data.ProductData;
import dabang.star.cafe.application.data.TypeCategoryData;
import dabang.star.cafe.domain.category.CategoryRepository;
import dabang.star.cafe.domain.category.CategoryType;
import dabang.star.cafe.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberFunctionService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public List<TypeCategoryData> getAllCategories() {
        List<TypeCategoryData> typeCategories = new ArrayList<>();
        CategoryType[] types = CategoryType.values();

        for (CategoryType type : types) {
            List<CategoryData> categoryData = categoryRepository.findAllByType(type.getKey());
            typeCategories.add(new TypeCategoryData(type, categoryData));
        }

        return typeCategories;
    }

    public List<ProductData> getProductsByCategoryId(int categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }

}
