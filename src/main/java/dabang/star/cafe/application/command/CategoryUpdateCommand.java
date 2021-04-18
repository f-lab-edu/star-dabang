package dabang.star.cafe.application.command;

import dabang.star.cafe.domain.category.Category;
import dabang.star.cafe.domain.category.CategoryType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CategoryUpdateCommand {

    @NotNull(message = "invalid name")
    @Size(max = 20, message = "invalid name")
    private String name;

    @NotNull(message = "invalid category name")
    private CategoryType categoryType;

    public Category toCategory(int categoryId) {

        return Category.builder()
                .id(categoryId)
                .name(name)
                .categoryType(categoryType)
                .build();
    }

}
