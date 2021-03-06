package dabang.star.cafe.application;

import dabang.star.cafe.application.command.CategoryCreateCommand;
import dabang.star.cafe.application.command.CategoryUpdateCommand;
import dabang.star.cafe.application.data.CategoryData;
import dabang.star.cafe.application.exception.DuplicatedException;
import dabang.star.cafe.application.exception.ResourceNotFoundException;
import dabang.star.cafe.domain.category.Category;
import dabang.star.cafe.domain.category.CategoryRepository;
import dabang.star.cafe.domain.category.CategoryType;
import dabang.star.cafe.utils.CacheKey;
import dabang.star.cafe.utils.CacheName;
import dabang.star.cafe.utils.page.Page;
import dabang.star.cafe.utils.page.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CategoryAdminService {

    private final CategoryRepository categoryRepository;

    public CategoryType[] getCategoryTypes() {
        return CategoryType.values();
    }

    @CacheEvict(value = CacheName.CATEGORY, key = CacheKey.DEFAULT)
    public CategoryData createCategory(CategoryCreateCommand categoryCreateCommand) {

        Category category = categoryCreateCommand.toCategory();

        try {
            categoryRepository.save(category);
        } catch (DuplicateKeyException e) {
            throw new DuplicatedException(e);
        }

        return CategoryData.from(category);
    }

    @CacheEvict(value = CacheName.CATEGORY, key = CacheKey.DEFAULT)
    @Transactional
    public void updateCategory(int categoryId, CategoryUpdateCommand categoryUpdateCommand) {
        categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("category not found by id : " + categoryId)
        );

        categoryRepository.save(categoryUpdateCommand.toCategory(categoryId));
    }

    public Page<CategoryData> getCategories(Pagination pagination) {

        return categoryRepository.findAll(pagination);
    }

    @CacheEvict(value = CacheName.CATEGORY, key = CacheKey.DEFAULT)
    public void deleteCategory(int categoryId) {

        if (categoryRepository.deleteById(categoryId) == 0) {
            throw new ResourceNotFoundException("category not found by id : " + categoryId);
        }
    }

}
