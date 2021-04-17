package dabang.star.cafe.api;

import dabang.star.cafe.application.MemberFunctionService;
import dabang.star.cafe.application.data.ProductData;
import dabang.star.cafe.application.data.TypeCategoryData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberFunctionApi {

    private final MemberFunctionService memberFunctionService;

    /**
     * 사용자가 주문을 하기전 카테고리를 조회
     *
     * @return HttpStatus.OK (타입, 하위 카테고리들)목록 반환
     */
    @GetMapping("/categories")
    public List<TypeCategoryData> getAllCategories() {
        return memberFunctionService.getAllCategories();
    }

    /**
     * 사용자가 해당 카테고리에 대한 상품목록을 조회
     *
     * @param categoryId (카테고리 ID)
     * @return HttpStatus.OK (상품 목록)반환
     */
    @GetMapping("/categories/{categoryId}/products")
    public List<ProductData> getProductsByCategoryId(@PathVariable int categoryId) {
        return memberFunctionService.getProductsByCategoryId(categoryId);
    }

}
