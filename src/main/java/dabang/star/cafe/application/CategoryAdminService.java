package dabang.star.cafe.application;

import dabang.star.cafe.application.command.CategoryCreateCommand;
import dabang.star.cafe.application.data.CategoryData;
import dabang.star.cafe.application.data.EnumData;
import dabang.star.cafe.application.exception.DuplicatedException;
import dabang.star.cafe.domain.category.Category;
import dabang.star.cafe.domain.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

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

        Category category = categoryCreateCommand.toCategory();

        try {
            categoryRepository.save(category);
        } catch (DuplicateKeyException e) {
            throw new DuplicatedException(e.getCause().getMessage());
        }

        return CategoryData.from(category);
    }

}
