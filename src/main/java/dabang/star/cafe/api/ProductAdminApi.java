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
@RequestMapping("/products")
public class ProductAdminApi {

    private final ProductAdminService productAdminService;

    /**
     * 상품 이미지 업로드
     *
     * @param file (상품 이미지 파일)
     * @return 업로드 성공시 HttpStatus.Ok (이미지 파일 저장 경로) 반환
     */
    @LoginCheck(role = Role.ADMIN)
    @PostMapping("/images")
    public String uploadProductImage(@RequestParam MultipartFile file) {
        return productAdminService.uploadProductImage(file);
    }

    /**
     * 상품 등록
     *
     * @param productCreateCommand (name, categoryId, price, description, image, options)
     * @return 상품 등록 완료시 HttpStatus.CREATED (Product) 반환
     */
    @LoginCheck(role = Role.ADMIN)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Product createProduct(@Valid @RequestBody ProductCreateCommand productCreateCommand) {
        return productAdminService.createProduct(productCreateCommand);
    }

    /**
     * 상품을 포함하여 관련 옵션 설정까지 삭제
     *
     * @param productId (삭제할 상품 id)
     */
    @LoginCheck(role = Role.ADMIN)
    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable long productId) {
        productAdminService.deleteProduct(productId);
    }

    /**
     * 상품을 관리하기 위해 상품 리스트를 모두 조회
     *
     * @param pagination (page, size)
     * @return 조회 완료시 HttpStatus.OK (Page<ProductData>) 반환
     */
    @LoginCheck(role = Role.ADMIN)
    @GetMapping
    public Page<ProductData> getAllOption(Pagination pagination) {
        return productAdminService.getAllProduct(pagination);
    }

    /**
     * 상품에 대한 정보를 업데이트 하며 새로운 옵션을 추가하거나 삭제
     *
     * @param productUpdateCommand (name, categoryId, price, description, image, options, deleteOptions, addOptions)
     * @param productId            (업데이트 상품의 id)
     */
    @LoginCheck(role = Role.ADMIN)
    @PatchMapping("/{productId}")
    public void updateProduct(@Valid @RequestBody ProductUpdateCommand productUpdateCommand,
                              @PathVariable long productId) {
        productAdminService.updateProduct(productUpdateCommand, productId);
    }

}
