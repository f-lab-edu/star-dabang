package dabang.star.cafe.application;

import dabang.star.cafe.application.command.CategoryCreateCommand;
import dabang.star.cafe.application.command.CategoryUpdateCommand;
import dabang.star.cafe.application.data.CategoryData;
import dabang.star.cafe.application.exception.DuplicatedException;
import dabang.star.cafe.application.exception.ResourceNotFoundException;
import dabang.star.cafe.domain.category.Category;
import dabang.star.cafe.domain.category.CategoryRepository;
import dabang.star.cafe.domain.category.CategoryType;
import dabang.star.cafe.utils.page.Page;
import dabang.star.cafe.utils.page.Pagination;
import lombok.RequiredArgsConstructor;
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

    public CategoryData createCategory(CategoryCreateCommand categoryCreateCommand) {

        Category category = categoryCreateCommand.toCategory();

        try {
            categoryRepository.save(category);
        } catch (DuplicateKeyException e) {
            throw new DuplicatedException(e);
        }

        return CategoryData.from(category);
    }

    @Transactional
    public void updateCategory(CategoryUpdateCommand categoryUpdateCommand) {

        categoryRepository.findById(categoryUpdateCommand.getId()).orElseThrow(
                () -> new ResourceNotFoundException("category not found by id : " + categoryUpdateCommand.getId())
        );

        categoryRepository.save(categoryUpdateCommand.toCategory());
    }

    public Page<CategoryData> getCategories(Pagination pagination) {

        return categoryRepository.findAll(pagination);
    }

    public void deleteCategory(int categoryId) {

        if (categoryRepository.deleteById(categoryId) == 0) {
            throw new ResourceNotFoundException("category not found by id : " + categoryId);
        }
    }

}
