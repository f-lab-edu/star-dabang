package dabang.star.cafe.api;

import dabang.star.cafe.api.aop.LoginCheck;
import dabang.star.cafe.application.CategoryAdminService;
import dabang.star.cafe.application.command.CategoryCreateCommand;
import dabang.star.cafe.application.command.CategoryUpdateCommand;
import dabang.star.cafe.application.data.CategoryData;
import dabang.star.cafe.domain.category.CategoryType;
import dabang.star.cafe.domain.manager.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryAdminApi {

    private final CategoryAdminService categoryAdminService;

    /**
     * Admin이 새로운 카테고리를 등록하기전에 타입을 지정하기 위해서 조회
     *
     * @return 조회 완료시 HttpStatus.Ok 카테고리 유형 목록 반환
     */
    @LoginCheck(role = Role.ADMIN)
    @GetMapping("/type")
    public CategoryType[] getTypes() {

        return categoryAdminService.getCategoryTypes();
    }

    /**
     * Admin이 새로운 카테고리를 등록
     *
     * @param categoryCreateCommand (name, categoryType)
     * @return 등록 완료시 HttpStatus.CREATED (CategoryData) 반환
     */
    @LoginCheck(role = Role.ADMIN)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryData createCategory(@Valid @RequestBody CategoryCreateCommand categoryCreateCommand) {

        return categoryAdminService.createCategory(categoryCreateCommand);
    }

    /**
     * Admin이 카테고리의 이름과 유형을 수정
     *
     * @param categoryUpdateCommand (id, name, categoryType)
     */
    @LoginCheck(role = Role.ADMIN)
    @PatchMapping
    public void updateCategory(@Valid @RequestBody CategoryUpdateCommand categoryUpdateCommand) {

        categoryAdminService.updateCategory(categoryUpdateCommand);
    }

}

