package dabang.star.cafe;

import com.google.cloud.secretmanager.v1.AccessSecretVersionResponse;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.google.cloud.secretmanager.v1.SecretVersionName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Profile("prod")
@Component
public class SecretManager {

    @Value("${spring.cloud.gcp.project-id}")
    private String projectId;

    private SecretManagerServiceClient secretManagerClient;

    @PostConstruct
    public void secretManagerServiceClient() throws IOException {
        secretManagerClient = SecretManagerServiceClient.create();
    }

    @PreDestroy
    public void clean() {
        if (secretManagerClient != null) {
            secretManagerClient.close();
        }
    }

    public String accessSecretVersion(String secretId, String versionId) {
        SecretVersionName secretVersionName = SecretVersionName.of(projectId, secretId, versionId);
        AccessSecretVersionResponse response = secretManagerClient.accessSecretVersion(secretVersionName);

        return response.getPayload().getData().toStringUtf8();
    }
}
