package dabang.star.cafe.api;

import dabang.star.cafe.api.aop.LoginCheck;
import dabang.star.cafe.application.CategoryAdminService;
import dabang.star.cafe.application.command.CategoryCreateCommand;
import dabang.star.cafe.application.data.CategoryData;
import dabang.star.cafe.application.data.EnumData;
import dabang.star.cafe.domain.manager.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryAdminApi {

    private final CategoryAdminService categoryAdminService;

    /**
     * Admin이 새로운 카테고리를 등록하기전에 타입을 지정하기 위해서 조회
     *
     * @return 조회 완료시 HttpStatus.Ok (List<EnumData>) 반환
     */
    @LoginCheck(role = Role.ADMIN)
    @GetMapping("/type")
    public List<EnumData> getTypes() {

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

}

