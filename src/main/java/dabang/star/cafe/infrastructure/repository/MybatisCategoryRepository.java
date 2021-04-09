package dabang.star.cafe.infrastructure.repository;

import dabang.star.cafe.domain.category.Category;
import dabang.star.cafe.domain.category.CategoryRepository;
import dabang.star.cafe.infrastructure.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
    public Optional<Category> findById(int id) {

        return categoryMapper.getById(id);
    }

}
