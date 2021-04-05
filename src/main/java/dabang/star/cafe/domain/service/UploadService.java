package dabang.star.cafe.domain.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {

    String upload(MultipartFile multipartFile, String dirPrefix) throws IOException;
}
