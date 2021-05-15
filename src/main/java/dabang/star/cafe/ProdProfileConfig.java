package dabang.star.cafe;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import javax.sql.DataSource;

@Profile("prod")
@DependsOn(value = "secretManager")
@Configuration
@RequiredArgsConstructor
public class ProdProfileConfig {

    private final SecretManager secretManager;

    @Bean
    public DataSource dataSource() {
        var dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://10.174.0.10:3306/stardabangdb?characterEncoding=utf8");
        dataSourceBuilder.username(secretManager.accessSecretVersion("db-username", "1"));
        dataSourceBuilder.password(secretManager.accessSecretVersion("db-password", "1"));
        return dataSourceBuilder.build();
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("10.174.0.21");
        redisStandaloneConfiguration.setPort(6379);
        redisStandaloneConfiguration.setPassword(secretManager.accessSecretVersion("redis-password", "1"));
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

}
