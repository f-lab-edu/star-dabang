package dabang.star.cafe.application;

import dabang.star.cafe.application.command.ProductCreateCommand;
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
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ValidationException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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

    public Product createProduct(ProductCreateCommand productCreateCommand) {
        Product product = productCreateCommand.toProduct();

        int categoryId = product.getCategoryId();
        categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("category id does not exist : " + categoryId)
        );

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

        try {
            productRepository.save(product);
        } catch (DuplicateKeyException e) {
            throw new DuplicatedException(e.getCause().getMessage());
        }

        return product;
    }

}
