package dabang.star.cafe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.util.SocketUtils;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;


@Profile("test")
@Configuration
public class EmbeddedRedisConfig {

    private static int embeddedRedisPort;

    private RedisServer redisServer;

    @PostConstruct
    public void redisServer() throws IOException {
        embeddedRedisPort = SocketUtils.findAvailableTcpPort();
        redisServer = new RedisServer(embeddedRedisPort);
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }


    @Profile("test")
    @Configuration
    @DependsOn(value = "embeddedRedisConfig")
    static class RedisRepositoryConfig {

        @Bean
        public RedisConnectionFactory redisConnectionFactory() {
            return new LettuceConnectionFactory("localhost", embeddedRedisPort);
        }

    }

}
