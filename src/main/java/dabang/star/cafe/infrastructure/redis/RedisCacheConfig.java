package dabang.star.cafe.infrastructure.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

/**
 * Spring Boot Default Client인 Lettuce 사용
 * Cache Key Serializer는 Default인 StringRedisSerializer 사용
 * Cache Value Serializer는 다양한 데이터 타입을 위해  GenericJackson2JsonRedisSerializer 사용
 * disableCachingNullValues으로 Null은 캐싱에서 제외
 * 카테고리, 상품, 매장 정보는 변동사항이 크게 없으므로 TTL 미설정
 */
@RequiredArgsConstructor
@Configuration
public class RedisCacheConfig {

    private final RedisConnectionFactory connectionFactory;

    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .disableCachingNullValues();

        return RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(connectionFactory)
                .cacheDefaults(configuration).build();
    }

}
