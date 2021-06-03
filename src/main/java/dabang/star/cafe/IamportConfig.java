package dabang.star.cafe;

import com.siot.IamportRestClient.IamportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@Configuration
public class IamportConfig {

    @Value("${iamport.client.api-key}")
    private String iamportApiKey;

    @Value("${iamport.client.api-secret}")
    private String iamportApiSecret;

    @Bean
    public IamportClient iamportClient() {
        return new IamportClient(iamportApiKey, iamportApiSecret);
    }
}
