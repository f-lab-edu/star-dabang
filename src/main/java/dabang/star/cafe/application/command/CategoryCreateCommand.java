package dabang.star.cafe.application.command;

import dabang.star.cafe.domain.category.Category;
import dabang.star.cafe.domain.category.CategoryType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CategoryCreateCommand {

    @NotBlank(message = "invalid name")
    private String name;

    @NotNull(message = "invalid category type")
    private CategoryType categoryType;

    public Category toCategory() {

        return Category.builder()
                .name(name)
                .categoryType(categoryType)
                .build();
    }

}
