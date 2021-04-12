package dabang.star.cafe.api;

import dabang.star.cafe.api.aop.LoginCheck;
import dabang.star.cafe.application.ProductAdminService;
import dabang.star.cafe.domain.manager.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

}
