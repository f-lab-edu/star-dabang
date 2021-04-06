package dabang.star.cafe.infrastructure.repository;

import dabang.star.cafe.application.data.CategoryData;
import dabang.star.cafe.domain.category.Category;
import dabang.star.cafe.domain.category.CategoryRepository;
import dabang.star.cafe.infrastructure.mapper.CategoryMapper;
import dabang.star.cafe.utils.page.Page;
import dabang.star.cafe.utils.page.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MybatisCategoryRepository implements CategoryRepository {

    private final CategoryMapper categoryMapper;

    @Override
    public Integer save(Category category) {

        if (category.getId() == null) {
            categoryMapper.insert(category);
        } else {
            categoryMapper.update(category);
        }

        return category.getId();

    }

    @Override
    public boolean existsByName(String name) {

        return categoryMapper.exists(name);
    }

    @Override
    public Optional<Category> findById(int id) {

        return categoryMapper.getById(id);
    }

    @Override
    public Page<CategoryData> findAll(Pagination pagination) {

        int size = pagination.getSize();
        int offset = pagination.getOffset();
        int page = pagination.getPage();

        List<CategoryData> categories = categoryMapper.selectAll(size, offset);
        int totalCount = categoryMapper.getAllCount();

        return new Page<>(categories, totalCount, size, page);
    }

}
