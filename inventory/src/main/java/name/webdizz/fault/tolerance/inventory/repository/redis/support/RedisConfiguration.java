package name.webdizz.fault.tolerance.inventory.repository.redis.support;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import name.webdizz.fault.tolerance.inventory.domain.Inventory;

@Configuration
class RedisConfiguration {

    @Bean
    public RedisTemplate<String, Inventory> inventoryRedisTemplate(final RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Inventory> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
