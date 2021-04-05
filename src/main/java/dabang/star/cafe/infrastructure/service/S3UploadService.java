package dabang.star.cafe.infrastructure.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import dabang.star.cafe.domain.service.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3UploadService implements UploadService {

    private final AmazonS3Client s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    /**
     * 1. MultipartFile을 전달 받아 File로 전환.
     * 2. S3에 Public 읽기 권한으로 업로드하고 로컬에 생성된 파일 삭제 후 S3 객체 URL 반환.
     */
    public String upload(MultipartFile multipartFile, String dirPrefix) throws IOException {
        File uploadFile = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("파일 변환에 실패하였습니다."));
        String fileName = dirPrefix + "/" + uploadFile.getName();

        return putS3(uploadFile, fileName);
    }

    private String putS3(File uploadFile, String fileName) {
        s3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile)
                .withCannedAcl(CannedAccessControlList.PublicRead)
        );

        log.info(uploadFile.delete() ? "파일이 삭제되었습니다." : "파일이 삭제되지 않았습니다.");

        return s3Client.getUrl(bucket, fileName).toString();
    }

    private Optional<File> convert(MultipartFile multipartFile) throws IOException {
        File convertFile = new File(multipartFile.getOriginalFilename());
        if (convertFile.createNewFile()) {
            try (FileOutputStream fileOutputStream = new FileOutputStream(convertFile)) {
                fileOutputStream.write(multipartFile.getBytes());
            }
            return Optional.of(convertFile);
        }

        return Optional.empty();
    }

}
