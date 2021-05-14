package dabang.star.cafe;

import com.google.cloud.secretmanager.v1.AccessSecretVersionResponse;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.google.cloud.secretmanager.v1.SecretVersionName;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.io.IOException;

@Profile("prod")
@Configuration
public class ProdProfileConfig {

    private static final String PROJECT_ID = "turnkey-energy-312111";

    private static SecretManagerServiceClient secretManagerClient;

    @PostConstruct
    public void secretManagerServiceClient() throws IOException {
        secretManagerClient = SecretManagerServiceClient.create();
    }

    @PreDestroy
    public void clean() {
        secretManagerClient.close();
    }

    @Bean
    public DataSource dataSource() {
        var dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://10.174.0.10:3306/stardabangdb?characterEncoding=utf8");
        dataSourceBuilder.username(accessSecretVersion("db-username", "1"));
        dataSourceBuilder.password(accessSecretVersion("db-password", "1"));
        return dataSourceBuilder.build();
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("10.174.0.21");
        redisStandaloneConfiguration.setPort(6379);
        redisStandaloneConfiguration.setPassword(accessSecretVersion("redis-password", "1"));
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }


    private String accessSecretVersion(String secretId, String versionId) {
        SecretVersionName secretVersionName = SecretVersionName.of(PROJECT_ID, secretId, versionId);
        AccessSecretVersionResponse response = secretManagerClient.accessSecretVersion(secretVersionName);

        return response.getPayload().getData().toStringUtf8();
    }
}
