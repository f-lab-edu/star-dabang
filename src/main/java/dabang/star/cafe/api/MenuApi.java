package dabang.star.cafe.api;

import dabang.star.cafe.api.aop.LoginCheck;
import dabang.star.cafe.api.aop.SessionId;
import dabang.star.cafe.application.MenuService;
import dabang.star.cafe.application.command.MyMenuCreateCommand;
import dabang.star.cafe.application.data.MyMenuInfoData;
import dabang.star.cafe.application.data.ProductData;
import dabang.star.cafe.application.data.TypeCategoryData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MenuApi {

    private final MenuService menuService;

    /**
     * 사용자가 주문을 하기전 카테고리를 조회
     *
     * @return HttpStatus.OK (타입, 하위 카테고리들)목록 반환
     */
    @GetMapping("/categories")
    public List<TypeCategoryData> getAllCategories() {
        return menuService.getAllCategories();
    }

    /**
     * 사용자가 해당 카테고리에 대한 상품목록을 조회
     *
     * @param categoryId (카테고리 ID)
     * @return HttpStatus.OK (상품 목록)반환
     */
    @GetMapping("/categories/{categoryId}/products")
    public List<ProductData> getProductsByCategoryId(@PathVariable int categoryId) {
        return menuService.getProductsByCategoryId(categoryId);
    }

    /**
     * 나의 메뉴 등록
     *
     * @param myMenuCreateCommand (나의 메뉴 이름, 상품, 상품 옵션)
     * @param memberId            (현재 요청하는 회원의 ID)
     */
    @LoginCheck
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/my-menus")
    public void registerMyMenu(@Valid @RequestBody MyMenuCreateCommand myMenuCreateCommand,
                               @SessionId Long memberId) {

        menuService.createMyMenu(memberId, myMenuCreateCommand);
    }

    /**
     * 나의 메뉴 조회
     *
     * @param memberId (현재 요청하는 회원의 ID)
     * @return (나의 메뉴 목록)
     */
    @LoginCheck
    @GetMapping("/my-menus")
    public List<MyMenuInfoData> getMyMenu(@SessionId Long memberId) {
        return menuService.getMyMenu(memberId);
    }

    /**
     * 나의 메뉴 삭제
     *
     * @param myMenuId (삭제할 나의 메뉴 ID)
     * @param memberId (현재 요청하는 회원의 ID)
     */
    @LoginCheck
    @DeleteMapping("/my-menus/{myMenuId}")
    public void deleteMyMenu(@PathVariable long myMenuId,
                             @SessionId Long memberId) {
        menuService.deleteMyMenu(myMenuId, memberId);
    }

}
