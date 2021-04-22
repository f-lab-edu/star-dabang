package dabang.star.cafe.api;

import dabang.star.cafe.api.aop.LoginCheck;
import dabang.star.cafe.application.ProductAdminService;
import dabang.star.cafe.application.command.ProductCreateCommand;
import dabang.star.cafe.application.command.ProductUpdateCommand;
import dabang.star.cafe.application.data.ProductData;
import dabang.star.cafe.domain.manager.Role;
import dabang.star.cafe.domain.product.Product;
import dabang.star.cafe.utils.page.Page;
import dabang.star.cafe.utils.page.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class ProductAdminApi {

    private final ProductAdminService productAdminService;

    /**
     * 상품 이미지 업로드
     *
     * @param file (상품 이미지 파일)
     * @return HttpStatus.Ok (이미지 파일 저장 경로) 반환
     */
    @LoginCheck(role = Role.ADMIN)
    @PostMapping("/products/images")
    public String uploadProductImage(@RequestParam MultipartFile file) {
        return productAdminService.uploadProductImage(file);
    }

    /**
     * 새로운 상품을 등록
     *
     * @param productCreateCommand (카테고리 이름, 상품 가격, 상품 설명, 이미지 URL, 상품의 옵션목록)
     * @param categoryId           (카테고리 아이디)
     * @return HttpStatus.CREATED (저장된 상품) 반환
     */
    @LoginCheck(role = Role.ADMIN)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/categories/{categoryId}/products")
    public Product createProduct(@Valid @RequestBody ProductCreateCommand productCreateCommand,
                                 @PathVariable int categoryId) {
        return productAdminService.createProduct(categoryId, productCreateCommand);
    }

    /**
     * 해당 카테고리의 상품을 삭제
     *
     * @param categoryId (카테고리 아이디)
     * @param productId  (상품 아이디)
     */
    @LoginCheck(role = Role.ADMIN)
    @DeleteMapping("/categories/{categoryId}/products/{productId}")
    public void deleteProduct(@PathVariable int categoryId, @PathVariable long productId) {
        productAdminService.deleteProduct(categoryId, productId);
    }

    /**
     * 상품을 관리하기 위해 상품 목록을 페이징으로 조회
     *
     * @param pagination (page, size)
     * @return HttpStatus.OK (상품 목록 페이지) 반환
     */
    @LoginCheck(role = Role.ADMIN)
    @GetMapping("/products")
    public Page<ProductData> getAllOption(Pagination pagination) {
        return productAdminService.getAllProduct(pagination);
    }
    
    /**
     * 상품에 대한 정보를 업데이트
     *
     * @param productUpdateCommand (상품 이름, 상품 가격, 상품 설명, 이미지 URL, 변경 옵션 목록, 삭제 옵션 목록, 추가 옵션 목록)
     * @param categoryId           (카테고리 아이디)
     * @param productId            (상품 아이디)
     */
    @LoginCheck(role = Role.ADMIN)
    @PatchMapping("/categories/{categoryId}/products/{productId}")
    public void updateProduct(@Valid @RequestBody ProductUpdateCommand productUpdateCommand,
                              @PathVariable int categoryId,
                              @PathVariable long productId) {
        productAdminService.updateProduct(categoryId, productId, productUpdateCommand);
    }

}
