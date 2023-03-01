package com.shop.site.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
public class TokenRepository {
    @Value("${jwt.expiration-time}")
    private long expiration;
    private RedisTemplate<String, Object> redisTemplate;

    public TokenRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public void save(String token) {
        redisTemplate.opsForHash().put(token, token, token);
        redisTemplate.expire(token, Duration.ofSeconds(expiration));
    }

    public String findByToken(String id) {
        return (String) redisTemplate.opsForHash().get(id, id);
    }
}
