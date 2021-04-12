package dabang.star.cafe.application;

import dabang.star.cafe.application.exception.FileUploadException;
import dabang.star.cafe.domain.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ValidationException;
import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
@Service
public class ProductAdminService {

    private static final String PRODUCT_UPLOAD_PATH = "product";
    private static final String[] EXTENSIONS = {"image/png", "image/jpg"};

    private final UploadService uploadService;

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

}
