package dabang.star.cafe.api;

import dabang.star.cafe.api.aop.LoginCheck;
import dabang.star.cafe.application.ProductAdminService;
import dabang.star.cafe.application.command.ProductCreateCommand;
import dabang.star.cafe.domain.manager.Role;
import dabang.star.cafe.domain.product.Product;
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

}
