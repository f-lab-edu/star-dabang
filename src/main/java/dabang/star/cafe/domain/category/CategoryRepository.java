package dabang.star.cafe.domain.category;

import dabang.star.cafe.application.data.CategoryData;
import dabang.star.cafe.utils.page.Page;
import dabang.star.cafe.utils.page.Pagination;

import java.util.Optional;

public interface CategoryRepository {

    Integer save(Category category);

    boolean existsByName(String name);

    Optional<Category> findById(int id);

    Page<CategoryData> findAll(Pagination pagination);
    
}
