package dabang.star.cafe.application;

import dabang.star.cafe.application.command.CategoryCreateCommand;
import dabang.star.cafe.application.command.CategoryUpdateCommand;
import dabang.star.cafe.application.data.CategoryData;
import dabang.star.cafe.application.data.EnumData;
import dabang.star.cafe.application.exception.DuplicatedException;
import dabang.star.cafe.application.exception.ResourceNotFoundException;
import dabang.star.cafe.domain.category.Category;
import dabang.star.cafe.domain.category.CategoryRepository;
import dabang.star.cafe.utils.page.Page;
import dabang.star.cafe.utils.page.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static dabang.star.cafe.application.EnumMapperService.CATEGORY_TYPE;

@RequiredArgsConstructor
@Service
public class CategoryAdminService {

    private final EnumMapperService enumMapperService;
    private final CategoryRepository categoryRepository;

    public List<EnumData> getCategoryTypes() {

        return enumMapperService.get(CATEGORY_TYPE);
    }

    public CategoryData createCategory(CategoryCreateCommand categoryCreateCommand) {

        checkDuplicatedEmail(categoryCreateCommand.getName());

        Category category = categoryCreateCommand.toCategory();
        categoryRepository.save(category);

        return CategoryData.from(category);
    }

    private void checkDuplicatedEmail(String name) {

        if (categoryRepository.existsByName(name)) {
            throw new DuplicatedException("duplicated category name");
        }
    }

    @Transactional
    public void updateCategory(CategoryUpdateCommand categoryUpdateCommand) {

        categoryRepository.findById(categoryUpdateCommand.getId()).orElseThrow(
                () -> new ResourceNotFoundException("category not found")
        );

        categoryRepository.save(categoryUpdateCommand.toCategory());
    }

    public Page<CategoryData> getCategories(Pagination pagination) {

        Page<CategoryData> categories = categoryRepository.findAll(pagination);
        if (categories.getContent().size() == 0) {
            throw new ResourceNotFoundException("No Categories were found");
        }

        return categories;
    }

    public void deleteCategory(int categoryId) {

        if (categoryRepository.deleteById(categoryId) == 0) {
            throw new ResourceNotFoundException("category not found by id : " + categoryId);
        }
    }

}
