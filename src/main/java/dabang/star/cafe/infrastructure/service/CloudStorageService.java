package dabang.star.cafe.infrastructure.service;

import com.google.cloud.storage.*;
import dabang.star.cafe.domain.service.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Component
public class CloudStorageService implements UploadService {

    private final Storage storage;

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucket;

    private static final String UPLOAD_FOLDER = "upload/";

    /**
     * 1. MultipartFile을 전달 받아 로컬에 파일 쓰기.
     * 2. GCP Cloud Storage 에 Public 읽기 권한으로 업로드하고 로컬에 생성된 파일 삭제 후 객체 URL 반환.
     */
    @Override
    public String upload(MultipartFile multipartFile, String dirPrefix) throws IOException {
        Path uploadFilePath = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("파일 변환에 실패하였습니다."));
        String fileName = dirPrefix + "/" + uploadFilePath.getFileName();

        return putStorage(uploadFilePath, fileName);
    }

    private String putStorage(Path uploadFilePath, String fileName) throws IOException {
        BlobId blobId = BlobId.of(bucket, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setAcl(List.of(Acl.of(Acl.User.ofAllUsers(),Acl.Role.READER)))
                .build();

        Blob createdBlob = storage.create(blobInfo, Files.readAllBytes(uploadFilePath));

        Files.deleteIfExists(uploadFilePath);

        return createdBlob.getMediaLink();
    }

    private Optional<Path> convert(MultipartFile multipartFile) throws IOException {
        Path uploadFilePath = Path.of(UPLOAD_FOLDER + StringUtils.cleanPath(multipartFile.getOriginalFilename()));

        if (Files.notExists(uploadFilePath)) {
            return Optional.of(
                    Files.write(uploadFilePath, multipartFile.getBytes())
            );
        }

        Files.delete(uploadFilePath);
        return Optional.empty();
    }
}
