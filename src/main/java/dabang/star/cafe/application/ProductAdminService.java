package dabang.star.cafe.application;

import dabang.star.cafe.application.command.ProductCreateCommand;
import dabang.star.cafe.application.command.ProductUpdateCommand;
import dabang.star.cafe.application.data.ProductData;
import dabang.star.cafe.application.data.ProductOptionData;
import dabang.star.cafe.application.exception.DuplicatedException;
import dabang.star.cafe.application.exception.FileUploadException;
import dabang.star.cafe.application.exception.ResourceNotFoundException;
import dabang.star.cafe.domain.category.CategoryRepository;
import dabang.star.cafe.domain.option.Option;
import dabang.star.cafe.domain.option.OptionRepository;
import dabang.star.cafe.domain.product.Product;
import dabang.star.cafe.domain.product.ProductOption;
import dabang.star.cafe.domain.product.ProductRepository;
import dabang.star.cafe.domain.service.UploadService;
import dabang.star.cafe.utils.page.Page;
import dabang.star.cafe.utils.page.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ValidationException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductAdminService {

    private static final String PRODUCT_UPLOAD_PATH = "product";
    private static final String[] EXTENSIONS = {"image/png", "image/jpg"};

    private final UploadService uploadService;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final OptionRepository optionRepository;

    public String uploadProductImage(MultipartFile file) {
        if (file.isEmpty()) {
            throw new ValidationException("upload file is empty");
        }

        String mimeType = file.getContentType();
        for (String extension : EXTENSIONS) {
            if (mimeType.equals(extension)) {
                try {
                    return uploadService.upload(file, PRODUCT_UPLOAD_PATH);
                } catch (IOException e) {
                    throw new FileUploadException("product image file upload failed");
                }
            }
        }

        throw new ValidationException("upload file extension is not " + Arrays.toString(EXTENSIONS));
    }

    public Product createProduct(int categoryId, ProductCreateCommand productCreateCommand) {
        Product product = productCreateCommand.toProduct(categoryId);
        validProduct(product);

        try {
            productRepository.save(product);
        } catch (DuplicateKeyException e) {
            throw new DuplicatedException(e);
        }

        return product;
    }

    private void validProduct(Product product) {
        Integer categoryId = product.getCategoryId();
        if (categoryId != null) {
            categoryRepository.findById(categoryId).orElseThrow(
                    () -> new ResourceNotFoundException("category id does not exist : " + categoryId)
            );
        }

        List<ProductOption> options = product.getOptions();
        for (ProductOption option : options) {
            Long optionId = option.getOptionId();
            Option findOption = optionRepository.findById(optionId).orElseThrow(
                    () -> new ResourceNotFoundException("option id does not exist : " + optionId)
            );

            int quantity = option.getQuantity();
            if (quantity > findOption.getMaxQuantity()) {
                throw new ValidationException("quantity exceeded maximum amount of option id : " + optionId);
            }
        }
    }

    public void deleteProduct(int categoryId, long productId) {
        if (productRepository.deleteById(categoryId, productId) == 0) {
            throw new ResourceNotFoundException("product id : " + productId + " does not exist in category id : " + categoryId);
        }
    }

    public Page<ProductData> getAllProduct(Pagination pagination) {
        return productRepository.findAll(pagination);
    }

    @Transactional
    public void updateProduct(int categoryId, long productId, ProductUpdateCommand productUpdateCommand) {
        // 존재하는 상품인지 확인
        ProductData productData = productRepository.findByIdAndCategoryId(categoryId, productId).orElseThrow(
                () -> new ResourceNotFoundException("product id : " + productId + " does not exist in category id : " + categoryId)
        );

        // 상품에 존재하는 옵션인지 확인
        Product product = productUpdateCommand.toProduct(categoryId, productId);
        List<Long> optionId = productData.getOptions().stream().map(ProductOptionData::getOptionId).collect(Collectors.toList());
        product.getOptions().forEach(option -> {
            long id = option.getOptionId();
            if (!optionId.contains(id)) {
                throw new ResourceNotFoundException("option id does not exist for update : " + id);
            }
        });

        // 카테고리와 옵션에 대한 유효성 검증
        validProduct(product);

        // 삭제할 옵션
        List<Long> deleteOptions = productUpdateCommand.getDeleteOptions();
        if (productRepository.deleteOptionById(productId, deleteOptions) != deleteOptions.size()) {
            throw new ResourceNotFoundException("option id does not exist for delete : " + deleteOptions.toString());
        }

        // 추가할 옵션 (만약 상품에 대해서 옵션이 이미 같은것이 존재할 경우 중복키 예외 발생하므로 예외처리)
        List<ProductOption> addOptions = productUpdateCommand.getAddOptions();
        try {
            productRepository.saveOption(productId, addOptions);
            productRepository.save(product);
        } catch (DuplicateKeyException e) {
            throw new DuplicatedException(e);
        }
    }

}
