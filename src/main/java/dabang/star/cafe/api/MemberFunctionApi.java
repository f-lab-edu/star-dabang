package dabang.star.cafe.api;

import dabang.star.cafe.application.MemberFunctionService;
import dabang.star.cafe.application.data.TypeCategoryData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberFunctionApi {

    private final MemberFunctionService memberFunctionService;

    @GetMapping("/categories")
    public List<TypeCategoryData> getCategories() {
        return memberFunctionService.getCategories();
    }

}
